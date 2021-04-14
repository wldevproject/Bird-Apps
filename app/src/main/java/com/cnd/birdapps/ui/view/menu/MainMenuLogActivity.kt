package com.cnd.birdapps.ui.view.menu

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.cnd.birdapps.R
import com.cnd.birdapps.databinding.ActivityMainMenuLogBinding
import com.cnd.birdapps.utils.ConsData.ADMIN
import com.cnd.birdapps.utils.ConsData.statLogin

class MainMenuLogActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainMenuLogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainMenuLogBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navController = findNavController(R.id.nav_host_fragment_log)
        binding.navView.setupWithNavController(navController)
        hideBottomAppBar()

        statLogin = ADMIN
    }


    private fun hideBottomAppBar() {
        binding.run {
            navView.animate().setListener(object : AnimatorListenerAdapter() {
                var isCanceled = false
                override fun onAnimationEnd(animation: Animator?) {
                    if (isCanceled) return
                    navView.visibility = View.GONE
                }

                override fun onAnimationCancel(animation: Animator?) {
                    isCanceled = true
                }
            })
        }
    }
}