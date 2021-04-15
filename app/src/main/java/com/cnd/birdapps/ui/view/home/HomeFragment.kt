package com.cnd.birdapps.ui.view.home

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.cnd.birdapps.R
import com.cnd.birdapps.data.model.MessageEvent
import com.cnd.birdapps.databinding.FragmentHomeBinding
import com.cnd.birdapps.ui.view.kategory.KategoryActivity
import com.cnd.birdapps.ui.viewmodels.HomeViewModel
import com.cnd.birdapps.utils.ConsData.ADMIN
import com.cnd.birdapps.utils.ConsData.KAT_ANIS
import com.cnd.birdapps.utils.ConsData.KAT_BEO
import com.cnd.birdapps.utils.ConsData.KAT_JALAK
import com.cnd.birdapps.utils.ConsData.KAT_LOVEBIRD
import com.cnd.birdapps.utils.ConsData.KAT_MORE
import com.cnd.birdapps.utils.ConsData.stateKategory
import com.cnd.birdapps.utils.ConsData.stateLogin
import org.greenrobot.eventbus.EventBus

class HomeFragment : Fragment() {
    private lateinit var viewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

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
        EventBus.getDefault().postSticky(MessageEvent(false))

        binding.apply {
            btnMorfologi.setOnClickListener { onMorphology() }
            btnMastering.setOnClickListener { onMastering() }
            btnArticle.setOnClickListener { onArticle() }
            btnAbout.setOnClickListener { onAbout() }

            btnKatAnis.setOnClickListener { onKatAnis() }
            btnKatBeo.setOnClickListener { onKatBeo() }
            btnKatJalak.setOnClickListener { onKatJalak() }
            btnKatLovebird.setOnClickListener { onKatLovebird() }
            btnKatMore.setOnClickListener { onKatMore() }
        }

    }

    private fun onKatAnis() {
        val intent = Intent(requireContext(), KategoryActivity::class.java)
        startActivity(intent)
        stateKategory = KAT_ANIS
    }

    private fun onKatBeo() {
        val intent = Intent(requireContext(), KategoryActivity::class.java)
        startActivity(intent)
        stateKategory = KAT_BEO
    }

    private fun onKatJalak() {
        val intent = Intent(requireContext(), KategoryActivity::class.java)
        startActivity(intent)
        stateKategory = KAT_JALAK
    }

    private fun onKatLovebird() {
        val intent = Intent(requireContext(), KategoryActivity::class.java)
        startActivity(intent)
        stateKategory = KAT_LOVEBIRD
    }

    private fun onKatMore() {
        val intent = Intent(requireContext(), KategoryActivity::class.java)
        startActivity(intent)
        stateKategory = KAT_MORE
    }


    private fun onMorphology() {
        if (stateLogin == ADMIN) {
            findNavController().navigate(R.id.morphologyFragment2)
        } else {
            findNavController().navigate(R.id.morphologyFragment)
        }
    }

    private fun onMastering() {
        if (stateLogin == ADMIN) {
            findNavController().navigate(R.id.masteringFragment2)
        } else {
            findNavController().navigate(R.id.masteringFragment)
        }
    }

    private fun onArticle() {
        if (stateLogin == ADMIN) {
            findNavController().navigate(R.id.articleFragment2)
        } else {
            findNavController().navigate(R.id.articleFragment)
        }
    }

    private fun onAbout() {
        if (stateLogin == ADMIN) {
            findNavController().navigate(R.id.aboutFragment)
        } else {
            findNavController().navigate(R.id.navigation_signIn)
            Toast.makeText(requireContext(), "Silahkan masuk terlebih dahulu", Toast.LENGTH_SHORT)
                .show()
        }
    }


    override fun onStart() {
        super.onStart()
        EventBus.getDefault().postSticky(MessageEvent(false))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}