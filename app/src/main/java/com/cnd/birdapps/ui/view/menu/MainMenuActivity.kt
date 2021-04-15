package com.cnd.birdapps.ui.view.menu

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.cnd.birdapps.R
import com.cnd.birdapps.data.model.MessageEvent
import com.cnd.birdapps.databinding.ActivityMainMenuBinding
import com.cnd.birdapps.utils.ConsData.USER
import com.cnd.birdapps.utils.ConsData.stateLogin
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MainMenuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navController = findNavController(R.id.nav_host_fragment)
        binding.run {
            navView.setupWithNavController(navController)
        }
        stateLogin = USER
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

    //    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        menuInflater.inflate(R.menu.activity_main_drawer, menu)
//        return true
//    }
}