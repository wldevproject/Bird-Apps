package com.cnd.birdapps.ui.view.signup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.cnd.birdapps.databinding.FragmentSignUpBinding
import com.cnd.birdapps.ui.viewmodels.SignUpViewModel

class SignUpFragment : Fragment() {
    private val viewModel: SignUpViewModel by viewModels()
    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!

    companion object {
        private const val EMPTY_MSG = "Tidak Boleh Kosong"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.status.observe(viewLifecycleOwner, Observer {
            showNotif(it)
            binding.btnSignUp.visibility = View.VISIBLE
            binding.progressBar.visibility = View.GONE
        })

        binding.btnSignIn.setOnClickListener { onSignIn() }
        binding.btnSignUp.setOnClickListener { onSignUp() }
    }

    private fun onSignIn() {
        findNavController().navigateUp()
//        findNavController().popBackStack()
    }

    private fun onSignUp() {
        val fullName = binding.fullName.text.toString().trim { it <= ' ' }
        val userName = binding.username.text.toString().trim { it <= ' ' }
        val password = binding.password.text.toString().trim { it <= ' ' }
        var inputEmpty = false

        when {
            fullName.isEmpty() -> {
                inputEmpty = true
                binding.fullName.error = EMPTY_MSG
            }
            userName.isEmpty() -> {
                inputEmpty = true
                binding.username.error = EMPTY_MSG
            }
            password.isEmpty() -> {
                inputEmpty = true
                binding.password.error = EMPTY_MSG
            }
        }

        if (!inputEmpty) {
            viewModel.postData(fullName, userName, password)
            binding.btnSignUp.visibility = View.GONE
            binding.progressBar.visibility = View.VISIBLE
        }
    }

    private fun showNotif(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}