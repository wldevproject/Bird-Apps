package com.cnd.birdapps.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.drawerlayout.widget.DrawerLayout
import com.cnd.birdapps.R
import com.cnd.birdapps.databinding.ActivityMainMenuBinding
import com.google.android.material.navigation.NavigationView

class MainMenuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

    }

//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        menuInflater.inflate(R.menu.activity_main_drawer, menu)
//        return true
//    }
}