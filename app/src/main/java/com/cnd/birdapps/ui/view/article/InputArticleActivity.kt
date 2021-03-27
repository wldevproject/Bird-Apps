package com.cnd.birdapps.ui.view.article

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cnd.birdapps.databinding.ActivityInputArticleBinding

class InputArticleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInputArticleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}