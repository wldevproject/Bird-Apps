package com.cnd.birdapps.ui.view.profil

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cnd.birdapps.databinding.FragmentProfilBinding
import com.cnd.birdapps.ui.view.menu.MainMenuActivity
import com.cnd.birdapps.ui.viewmodels.ProfilViewModel
import com.cnd.birdapps.utils.ConsData.ADMIN
import com.cnd.birdapps.utils.ConsData.USER
import com.cnd.birdapps.utils.ConsData.name
import com.cnd.birdapps.utils.ConsData.role
import com.cnd.birdapps.utils.ConsData.username

class ProfilFragment : Fragment() {

    private lateinit var viewModel: ProfilViewModel
    private var _binding: FragmentProfilBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(ProfilViewModel::class.java)
        _binding = FragmentProfilBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            fullName.text = name
            userId.text = username
            var dataStatus = ""
            if (role == ADMIN) {
                dataStatus = "Admin"
            }
            if (role == USER) {
                dataStatus = "Pengguna"
            }

            status.text = dataStatus

            btnSignOut.setOnClickListener {
                val i = Intent(requireContext(), MainMenuActivity::class.java)
                startActivity(i)
                activity?.finishAffinity()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}