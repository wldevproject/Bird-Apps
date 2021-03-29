package com.cnd.birdapps.ui.view.sigin

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.cnd.birdapps.R
import com.cnd.birdapps.databinding.FragmentSigInBinding

class SigInFragment : Fragment() {
    private lateinit var viewModel: SigInViewModel
    private var _binding: FragmentSigInBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(SigInViewModel::class.java)
        _binding = FragmentSigInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.text.observe(viewLifecycleOwner, Observer {
            binding.text.text = it
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}