package com.cnd.birdapps.ui.view.article

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.cnd.birdapps.data.model.article.DataItem
import com.cnd.birdapps.databinding.ActivityDetailArticleBinding
import com.cnd.birdapps.ui.viewmodels.PublishViewModel

class DetailArticleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailArticleBinding
    private val viewModel: PublishViewModel by viewModels()
    private var status = ""

    companion object {
        const val EXTRA_DATA_DETAIL = "data_detail"
        const val AKSES = "akses_type"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val item = intent.getParcelableExtra<DataItem>(EXTRA_DATA_DETAIL)
        val akses = intent.getStringExtra(AKSES)

        Glide.with(this)
            .load(item?.image)
            .into(binding.imgBanner)
        binding.birdName.text = item?.birdSpecies?.name
        binding.kategori.text = item?.birdSpecies?.name
        binding.userName.text = item?.user?.username
        if (item?.publish == true) {
            status = "Publish"
            if (akses == "admin") {
                binding.btnPublish.text = "Pending"
                onUpdate(item.id.toString(), false)
            }
        } else {
            status = "Pending"
            if (akses == "admin") {
                binding.btnPublish.text = "Publish"
                onUpdate(item?.id.toString(), true)
            }
        }
        binding.publish.text = status
        binding.description.text = item?.description
        binding.updateDate.text = item?.createdAt

        binding.backArrow.setOnClickListener {
            finish()
        }
    }

    private fun onUpdate(id: String, publish: Boolean) {
        binding.btnPublish.visibility = View.VISIBLE
        binding.btnPublish.setOnClickListener {
            viewModel.postData(id, publish)
            binding.loading.loading.visibility = View.VISIBLE

        }

        binding.btnDelete.visibility = View.VISIBLE
        binding.btnDelete.setOnClickListener {
            viewModel.deletData(id.toInt())
            binding.loading.loading.visibility = View.VISIBLE
        }

        viewModel.status.observe(this, Observer {
            Handler(Looper.getMainLooper()).postDelayed({
                binding.loading.loading.visibility = View.GONE
                finish()
            }, 1000)
            showNotif(it)
        })
    }

    private fun showNotif(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}