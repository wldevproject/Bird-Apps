package com.cnd.birdapps.ui.view.menu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.cnd.birdapps.R
import com.cnd.birdapps.data.model.MessageEvent
import com.cnd.birdapps.databinding.ActivityMainMenuLogBinding
import com.cnd.birdapps.utils.ConsData.ADMIN
import com.cnd.birdapps.utils.ConsData.role
import com.cnd.birdapps.utils.ConsData.stateLogin
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MainMenuLogActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainMenuLogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainMenuLogBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navController = findNavController(R.id.nav_host_fragment_log)
        binding.run {
            navView.setupWithNavController(navController)
        }
        stateLogin = role
    }

    private fun hideBottomAppBar(hideMenu: Boolean) {
        binding.run {
            if (hideMenu)navView.visibility = View.GONE
            if (!hideMenu) navView.visibility = View.VISIBLE
        }
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    fun onEvent(event: MessageEvent) {
        hideBottomAppBar(event.hideMenu)
    }
}