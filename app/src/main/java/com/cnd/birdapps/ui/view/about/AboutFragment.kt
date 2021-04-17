package com.cnd.birdapps.ui.view.about

import android.graphics.Color
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.cnd.birdapps.R
import com.cnd.birdapps.data.model.MessageEvent
import com.cnd.birdapps.databinding.FragmentAboutBinding
import com.cnd.birdapps.ui.viewmodels.AboutViewModel
import org.greenrobot.eventbus.EventBus

class AboutFragment : Fragment() {

    private var _binding: FragmentAboutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAboutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        EventBus.getDefault().postSticky(MessageEvent(true))

        binding.apply {
            btnHelp.setTextColor(ContextCompat.getColor(requireContext(), R.color.purple))
            btnAbout.setTextColor(ContextCompat.getColor(requireContext(), R.color.grey))
            info.visibility = View.VISIBLE
            about.visibility = View.GONE

            btnHelp.setOnClickListener { onShowed("info") }
            btnAbout.setOnClickListener { onShowed("about") }
        }
    }

    fun onShowed(state: String) {
        binding.apply {
            if (state == "info") {
                btnHelp.setTextColor(ContextCompat.getColor(requireContext(), R.color.purple))
                btnAbout.setTextColor(ContextCompat.getColor(requireContext(), R.color.grey))
                info.visibility = View.VISIBLE
                about.visibility = View.GONE
            } else {
                btnHelp.setTextColor(ContextCompat.getColor(requireContext(), R.color.grey))
                btnAbout.setTextColor(ContextCompat.getColor(requireContext(), R.color.purple))
                info.visibility = View.GONE
                about.visibility = View.VISIBLE
            }
        }
    }
}