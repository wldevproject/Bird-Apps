package com.cnd.birdapps.ui.view.morphology

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cnd.birdapps.data.model.MessageEvent
import com.cnd.birdapps.databinding.FragmentMorphologyBinding
import org.greenrobot.eventbus.EventBus

class MorphologyFragment : Fragment() {

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

        binding.apply {
            btnAnimasi.setOnClickListener { }
            btnBurungBeo.setOnClickListener { }
            btnBurungKolibri.setOnClickListener { }
        }
    }
}