package com.cnd.birdapps.ui.view.mastering

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cnd.birdapps.data.model.Mastering
import com.cnd.birdapps.data.model.user.MessageEvent
import com.cnd.birdapps.databinding.FragmentPlayMasteringBinding
import com.cnd.birdapps.ui.adapter.MasteringAdapter
import com.cnd.birdapps.ui.viewmodels.PlayMasteringViewModel
import com.cnd.birdapps.utils.ConsData.CC_JENGGOT
import com.cnd.birdapps.utils.ConsData.CILILIN
import com.cnd.birdapps.utils.ConsData.JALAK_RIO
import com.cnd.birdapps.utils.ConsData.KENARI
import com.cnd.birdapps.utils.ConsData.LOVE_BIRD
import com.cnd.birdapps.utils.ConsData.birdSpecies
import org.greenrobot.eventbus.EventBus
import java.util.ArrayList

class PlayMasteringFragment : Fragment() {
    private lateinit var viewModel: PlayMasteringViewModel
    private var _binding: FragmentPlayMasteringBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: MasteringAdapter
    var mediaPlayer: MediaPlayer? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(PlayMasteringViewModel::class.java)
        _binding = FragmentPlayMasteringBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        EventBus.getDefault().postSticky(MessageEvent(true))
        initData()
    }

    private fun initData() {
        binding.apply {
            masteringList.adapter?.notifyDataSetChanged()
            masteringList.scheduleLayoutAnimation()
            masteringList.setHasFixedSize(true)
            ViewCompat.setNestedScrollingEnabled(masteringList, true)
        }
        onGetData()
    }

    private fun onGetData() {
        when (birdSpecies) {
            LOVE_BIRD -> {
                viewModel.loveBird.observe(viewLifecycleOwner, Observer {
                    onShowData(it)
                })
            }
            KENARI -> {
                viewModel.kenari.observe(viewLifecycleOwner, Observer {
                    onShowData(it)
                })
            }
            JALAK_RIO -> {
                viewModel.jalakrio.observe(viewLifecycleOwner, Observer {
                    onShowData(it)
                })
            }
            CILILIN -> {
                viewModel.cililin.observe(viewLifecycleOwner, Observer {
                    onShowData(it)
                })
            }
            CC_JENGGOT -> {
                viewModel.cucakjenggot.observe(viewLifecycleOwner, Observer {
                    onShowData(it)
                })
            }
        }

    }

    private fun onShowData(list: ArrayList<Mastering>) {
        binding.birdName.text = list[0].singer
        adapter = MasteringAdapter(list)
        binding.masteringList.adapter = adapter

        adapter.setOnItemClickCallback(object : MasteringAdapter.OnItemClickCallback {
            override fun onPlay(song: Int) {
                mediaPlayer = MediaPlayer.create(requireContext(), song)
                mediaPlayer?.start()
            }

            override fun onPause(song: Int) {
                mediaPlayer?.pause()
            }

            override fun onStop(song: Int) {
                mediaPlayer?.stop()
                mediaPlayer?.release()
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        mediaPlayer?.stop()
        mediaPlayer?.release()
    }
}