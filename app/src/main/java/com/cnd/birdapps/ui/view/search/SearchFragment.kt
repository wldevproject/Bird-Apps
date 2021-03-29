package com.cnd.birdapps.ui.view.search

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.cnd.birdapps.R
import com.cnd.birdapps.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {
    private lateinit var viewModel: SearchViewModel
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
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