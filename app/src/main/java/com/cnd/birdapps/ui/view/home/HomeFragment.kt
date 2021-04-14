package com.cnd.birdapps.ui.view.home

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.cnd.birdapps.R
import com.cnd.birdapps.data.model.user.MessageEvent
import com.cnd.birdapps.databinding.FragmentHomeBinding
import com.cnd.birdapps.ui.viewmodels.HomeViewModel
import com.cnd.birdapps.utils.ConsData.ADMIN
import com.cnd.birdapps.utils.ConsData.statLogin
import org.greenrobot.eventbus.EventBus

class HomeFragment : Fragment() {
    private lateinit var viewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    var status: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        EventBus.getDefault().postSticky(MessageEvent(status))

        binding.apply {
            btnMorfologi.setOnClickListener { onMorphology() }
            btnMastering.setOnClickListener { onMastering() }
            btnArticle.setOnClickListener { onArticle() }
            btnAbout.setOnClickListener { onAbout() }
        }

    }

    private fun onAbout() {
        if (statLogin == ADMIN) {
            Toast.makeText(requireContext(), "Sedang Menjadi Admin", Toast.LENGTH_SHORT).show()
        } else {
            findNavController().navigate(R.id.navigation_signIn)
            Toast.makeText(requireContext(), "Sedang Menjadi User", Toast.LENGTH_SHORT).show()
        }

//        if (status) {
//            EventBus.getDefault().postSticky(MessageEvent(status))
//            Toast.makeText(requireContext(), "unhide", Toast.LENGTH_SHORT).show()
//            binding.txtAbout.text = "unhide"
//            status = false
//        } else {
//            EventBus.getDefault().postSticky(MessageEvent(status))
//            Toast.makeText(requireContext(), "hide", Toast.LENGTH_SHORT).show()
//            binding.txtAbout.text = "hide"
//            status = true
//        }
    }

    private fun onArticle() {
        findNavController().navigate(R.id.articleFragment)
    }

    private fun onMastering() {
        findNavController().navigate(R.id.masteringFragment)
    }

    private fun onMorphology() {
        findNavController().navigate(R.id.morphologyFragment)
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().postSticky(MessageEvent(status))
        Toast.makeText(requireContext(), "onStart", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}