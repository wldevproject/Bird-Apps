package com.cnd.birdapps.ui.view.article

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.ViewCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.cnd.birdapps.data.model.article.DataItem
import com.cnd.birdapps.databinding.ActivityMyArticleBinding
import com.cnd.birdapps.ui.adapter.ArticleAdapter
import com.cnd.birdapps.ui.viewmodels.ArticleViewModel
import com.cnd.birdapps.utils.ConsData.ADMIN
import com.cnd.birdapps.utils.ConsData.stateLogin
import com.cnd.birdapps.utils.ConsData.userID

class MyArticleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMyArticleBinding
    private val viewModel: ArticleViewModel by viewModels()
    private lateinit var adapter: ArticleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initData()
    }

    private fun initData() {
        binding.apply {
            loading.loading.visibility = View.VISIBLE
            articleList.layoutManager = GridLayoutManager(applicationContext, 2)
            articleList.scheduleLayoutAnimation()
            articleList.setHasFixedSize(true)
            ViewCompat.setNestedScrollingEnabled(articleList, true)

            btnAddNote.setOnClickListener {
                val intent = Intent(this@MyArticleActivity, InputArticleActivity::class.java)
                startActivity(intent)
            }
        }
        onGetData()
    }

    private fun onGetData() {
        if (stateLogin == ADMIN) {
            val title = "Artikel Seluruh Akun"
            binding.textTitle.text = title
            viewModel.getAllData()
        } else {
            viewModel.getDataUserId(userID)
        }



        viewModel.items.observe(this, Observer {
            onShowData(it)
        })

        viewModel.status.observe(this, Observer {
            Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
        })
    }

    private fun onShowData(listItems: ArrayList<DataItem>) {
        if (listItems.isNullOrEmpty()) {
            binding.noData.noData.visibility = View.VISIBLE
        }

        adapter = ArticleAdapter(listItems)
        binding.articleList.adapter = adapter

        onLoading()

        adapter.setOnItemClickCallback(object : ArticleAdapter.OnItemClickCallback {
            override fun onClicked(data: DataItem) {
                val intent = Intent(this@MyArticleActivity, DetailArticleActivity::class.java)
                intent.putExtra(DetailArticleActivity.EXTRA_DATA_DETAIL, data)
                intent.putExtra(DetailArticleActivity.AKSES, "3")
                startActivity(intent)
            }

            override fun onStatus(data: String) {

            }
        })
    }

    override fun onStart() {
        super.onStart()
        if (stateLogin == ADMIN) {
            binding.loading.loading.visibility = View.VISIBLE
            viewModel.getAllData()
        } else {
            binding.loading.loading.visibility = View.VISIBLE
            viewModel.getDataUserId(userID)
        }
    }

    fun onLoading(){
        Handler(Looper.getMainLooper()).postDelayed({
            binding.loading.loading.visibility = View.GONE
        }, 1000)
    }
}