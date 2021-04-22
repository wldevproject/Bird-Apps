package com.cnd.birdapps.ui.view.morphology

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.cnd.birdapps.data.model.MessageEvent
import com.cnd.birdapps.databinding.FragmentMorphologyBinding
import com.cnd.birdapps.ui.viewmodels.AnimasUrlViewModel
import org.greenrobot.eventbus.EventBus

class MorphologyFragment : Fragment() {
    private val viewModel: AnimasUrlViewModel by viewModels()
    private var _binding: FragmentMorphologyBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMorphologyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        EventBus.getDefault().postSticky(MessageEvent(true))

        viewModel.getData()
        binding.loading.loading.visibility = View.VISIBLE

        viewModel.items.observe(requireActivity(), Observer { data ->
            binding.apply {
                Handler(Looper.getMainLooper()).postDelayed({
                    if (_binding != null) {
                        loading.loading.visibility = View.GONE
                        btnAnimasi.setOnClickListener { }
                        btnBurungBeo.setOnClickListener { onAnimasiBeo(data[1].url) }
                        btnBurungKolibri.setOnClickListener { onAnimasiKolibri(data[0].url) }
                    }
                }, 1000)
            }
        })
    }

    private fun onAnimasiKolibri(data: String) {
        val intent = Intent(requireContext(), AnimasUrlActivity::class.java)
        intent.putExtra(AnimasUrlActivity.EXTRA_DATA_3D_URL, data)
        startActivity(intent)
    }

    private fun onAnimasiBeo(data: String) {
        val intent = Intent(requireContext(), AnimasUrlActivity::class.java)
        intent.putExtra(AnimasUrlActivity.EXTRA_DATA_3D_URL, data)
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}