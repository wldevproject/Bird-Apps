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
import com.cnd.birdapps.utils.ConsData.ADMIN
import com.cnd.birdapps.utils.ConsData.USER
import com.cnd.birdapps.utils.ConsData.USER_ADD
import com.cnd.birdapps.utils.ConsData.role

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

//        Log.d("akses --->", akses.toString())
//        Log.d("item --->", item.toString())

        Glide.with(this)
            .load(item?.image)
//            .apply(RequestOptions().override(600, 600))
            .into(binding.imgBanner)
        binding.birdName.text = item?.birdSpecies?.name
        binding.kategori.text = item?.birdSpecies?.name
        binding.userName.text = item?.user?.username
        if (item?.publish == true) {
            status = "Publish"
            if (role == ADMIN || akses == USER_ADD) {
                ondelete(item.id.toString())
                if (role == ADMIN) {
                    binding.btnPublish.text = "Pending"
                    onUpdate(item.id.toString(), false)
                }
            }
        } else {
            status = "Pending"
            if (role == ADMIN || akses == USER_ADD) {
                ondelete(item?.id.toString())
                if (role == ADMIN) {
                    binding.btnPublish.text = "Publish"
                    onUpdate(item?.id.toString(), true)
                }
            }
        }

        refresh()

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
    }

    private fun refresh() {
        viewModel.status.observe(this, Observer {
            Handler(Looper.getMainLooper()).postDelayed({
                binding.loading.loading.visibility = View.GONE
                finish()
            }, 1000)
            showNotif(it)
        })
    }

    private fun ondelete(id: String) {
        binding.btnDelete.visibility = View.VISIBLE
        binding.btnDelete.setOnClickListener {
            viewModel.deletData(id.toInt())
            binding.loading.loading.visibility = View.VISIBLE
        }
    }

    private fun showNotif(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}