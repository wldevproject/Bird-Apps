package com.cnd.birdapps.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.cnd.birdapps.databinding.ActivitySplashScreenBinding
import com.cnd.birdapps.ui.view.menu.MainMenuActivity

class SplashSceenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding

    companion object{
        private const val SPLASH_TIME_OUT = 3000
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Handler(Looper.getMainLooper()).postDelayed({
            val i = Intent(this, MainMenuActivity::class.java)
            startActivity(i)
            finish()
        }, SPLASH_TIME_OUT.toLong())
    }
}