package com.cnd.birdapps.ui.view.article

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.cnd.birdapps.data.model.article.DataItem
import com.cnd.birdapps.databinding.ActivityDetailArticleBinding

class DetailArticleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailArticleBinding

    companion object {
        const val EXTRA_DATA_DETAIL = "data_detail"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val item = intent.getParcelableExtra<DataItem>(EXTRA_DATA_DETAIL)

        Glide.with(this)
            .load(item?.image)
            .into(binding.imgBanner)
        binding.birdName.text = item?.birdSpecies?.name
        binding.kategori.text = item?.birdSpecies?.name
        binding.userName.text = item?.user?.username
        val status = if (item?.publish == true) {
            "Publish"
        } else {
            "Pending"
        }
        binding.publish.text = status
        binding.description.text = item?.description
        binding.updateDate.text = item?.createdAt
    }
}