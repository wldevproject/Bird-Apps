package com.cnd.birdapps.ui.view.mastering

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.cnd.birdapps.R
import com.cnd.birdapps.data.model.MessageEvent
import com.cnd.birdapps.databinding.FragmentMasteringBinding
import com.cnd.birdapps.utils.ConsData.ADMIN
import com.cnd.birdapps.utils.ConsData.CC_JENGGOT
import com.cnd.birdapps.utils.ConsData.CILILIN
import com.cnd.birdapps.utils.ConsData.JALAK_RIO
import com.cnd.birdapps.utils.ConsData.KENARI
import com.cnd.birdapps.utils.ConsData.LOVE_BIRD
import com.cnd.birdapps.utils.ConsData.birdSpecies
import com.cnd.birdapps.utils.ConsData.stateLogin
import org.greenrobot.eventbus.EventBus

class MasteringFragment : Fragment() {
    private var _binding: FragmentMasteringBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMasteringBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        EventBus.getDefault().postSticky(MessageEvent(true))

        binding.apply {
            mlovebird.setOnClickListener { onLovebird() }
            mkenari.setOnClickListener { onKenari() }
            mjalakrio.setOnClickListener { onJalakRio() }
            mcililin.setOnClickListener { onCililin() }
            mcucakjenggot.setOnClickListener { onCucakJenggot() }
        }
    }

    private fun onLovebird() {
        if (stateLogin == ADMIN) {
            findNavController().navigate(R.id.playMasteringFragment2)
        } else {
            findNavController().navigate(R.id.playMasteringFragment)
        }
        birdSpecies = LOVE_BIRD
    }

    private fun onKenari() {
        if (stateLogin == ADMIN) {
            findNavController().navigate(R.id.playMasteringFragment2)
        } else {
            findNavController().navigate(R.id.playMasteringFragment)
        }
        birdSpecies = KENARI
    }

    private fun onJalakRio() {
        if (stateLogin == ADMIN) {
            findNavController().navigate(R.id.playMasteringFragment2)
        } else {
            findNavController().navigate(R.id.playMasteringFragment)
        }
        birdSpecies = JALAK_RIO
    }

    private fun onCililin() {
        if (stateLogin == ADMIN) {
            findNavController().navigate(R.id.playMasteringFragment2)
        } else {
            findNavController().navigate(R.id.playMasteringFragment)
        }
        birdSpecies = CILILIN
    }

    private fun onCucakJenggot() {
        if (stateLogin == ADMIN) {
            findNavController().navigate(R.id.playMasteringFragment2)
        } else {
            findNavController().navigate(R.id.playMasteringFragment)
        }
        birdSpecies = CC_JENGGOT
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}