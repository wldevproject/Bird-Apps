package com.cnd.birdapps.ui.view.kategory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.activity.viewModels
import androidx.core.view.ViewCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.cnd.birdapps.data.model.article.DataItem
import com.cnd.birdapps.data.model.kategory.DataItemKat
import com.cnd.birdapps.databinding.ActivityListKategoriBinding
import com.cnd.birdapps.ui.adapter.ArticleAdapter
import com.cnd.birdapps.ui.view.article.DetailArticleActivity
import com.cnd.birdapps.ui.viewmodels.ArticleViewModel
import kotlinx.android.synthetic.main.activity_kategory.*
import java.util.ArrayList

class ListKategoriActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListKategoriBinding
    private val viewModelGrid: ArticleViewModel by viewModels()
    private lateinit var adapterGrid: ArticleAdapter

    companion object {
        const val EXTRA_ID_KATEGORI = "data_detail"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListKategoriBinding.inflate(layoutInflater)
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
        }

        val item = intent.getParcelableExtra<DataItemKat>(EXTRA_ID_KATEGORI)
        item?.let { onGetDataGrid(it.id) }
    }

    private fun onGetDataGrid(id: Int) {
        binding.backArrow.visibility = View.VISIBLE
        binding.backArrow.setOnClickListener {
            finish()
        }

        viewModelGrid.getDataQuery(id)
        viewModelGrid.items.observe(this, Observer {
            onShowDataGrid(it)
        })
    }


    private fun onShowDataGrid(list: ArrayList<DataItem>) {
        if (list.isNullOrEmpty()) {
            binding.noData.noData.visibility = View.VISIBLE
        }

        adapterGrid = ArticleAdapter(list)
        binding.articleList.adapter = adapterGrid

        Handler(Looper.getMainLooper()).postDelayed({
            binding.loading.loading.visibility = View.GONE
        }, 1000)


        adapterGrid.setOnItemClickCallback(object : ArticleAdapter.OnItemClickCallback {
            override fun onClicked(data: DataItem) {
                val intent = Intent(this@ListKategoriActivity, DetailArticleActivity::class.java)
                intent.putExtra(DetailArticleActivity.EXTRA_DATA_DETAIL, data)
                startActivity(intent)
            }

            override fun onStatus(data: String) {
                
            }

        })
    }
}