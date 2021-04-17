package com.cnd.birdapps.ui.view.signin

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.cnd.birdapps.R
import com.cnd.birdapps.databinding.FragmentSignInBinding
import com.cnd.birdapps.ui.view.menu.MainMenuLogActivity
import com.cnd.birdapps.ui.viewmodels.SignInViewModel
import com.cnd.birdapps.utils.ConsData

class SignInFragment : Fragment() {
    private lateinit var viewModel: SignInViewModel
    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!

    companion object {
        private const val EMPTY_MSG = "Tidak Boleh Kosong"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(SignInViewModel::class.java)
        _binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.item.observe(viewLifecycleOwner, Observer {
            binding.btnSignIn.visibility = View.VISIBLE
            binding.progressBar.visibility = View.GONE
            ConsData.apply {
                userID = it.id
                name = it.name
                username = it.username
                role = it.role
            }

            val i = Intent(requireContext(), MainMenuLogActivity::class.java)
            startActivity(i)
            activity?.finish()
        })

        viewModel.status.observe(viewLifecycleOwner, Observer {
            showNotif(it)
        })

        binding.btnSignUp.setOnClickListener { onSignUp() }
        binding.btnSignIn.setOnClickListener { onSignIn() }
    }

    private fun onSignUp() {
        findNavController().navigate(R.id.navigation_signUp)
    }

    private fun onSignIn() {
        val userName = binding.username.text.toString().trim { it <= ' ' }
        val password = binding.password.text.toString().trim { it <= ' ' }
        var inputEmpty = false

        when {
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
            viewModel.postDataTest(userName, password)
            binding.btnSignIn.visibility = View.GONE
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