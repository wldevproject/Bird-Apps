package com.cnd.birdapps.ui.view.kategory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.ViewCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.cnd.birdapps.data.model.article.DataItem
import com.cnd.birdapps.data.model.kategory.DataItemKat
import com.cnd.birdapps.databinding.ActivityKategoryBinding
import com.cnd.birdapps.ui.adapter.ArticleAdapter
import com.cnd.birdapps.ui.adapter.KategoryAdapter
import com.cnd.birdapps.ui.view.article.DetailArticleActivity
import com.cnd.birdapps.ui.viewmodels.ArticleViewModel
import com.cnd.birdapps.ui.viewmodels.KategoryViewModel
import com.cnd.birdapps.utils.ConsData.stateKategory
import java.util.ArrayList

class KategoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityKategoryBinding
    private val viewModel: KategoryViewModel by viewModels()
    private lateinit var adapter: KategoryAdapter

    private val viewModelGrid: ArticleViewModel by viewModels()
    private lateinit var adapterGrid: ArticleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initData()
    }

    private fun initData() {
        binding.apply {
            birdName.text = stateKategory

            kategoriList.adapter?.notifyDataSetChanged()
            kategoriList.scheduleLayoutAnimation()
            kategoriList.setHasFixedSize(true)
            ViewCompat.setNestedScrollingEnabled(kategoriList, true)

            articleList.layoutManager = GridLayoutManager(applicationContext, 2)
            articleList.scheduleLayoutAnimation()
            articleList.setHasFixedSize(true)
            ViewCompat.setNestedScrollingEnabled(articleList, true)
        }
        onGetData()
    }

    private fun onGetData() {
        binding.backArrow.visibility = View.GONE
        binding.kategoriList.visibility = View.VISIBLE
        binding.articleList.visibility = View.GONE

        viewModel.getData(stateKategory)
        viewModel.items.observe(this, Observer {
            onShowData(it)
        })

    }

    private fun onGetDataGrid(id: Int) {
        binding.backArrow.visibility = View.VISIBLE
        binding.kategoriList.visibility = View.GONE

        binding.backArrow.setOnClickListener {
            binding.backArrow.visibility = View.GONE
            binding.kategoriList.visibility = View.VISIBLE
            binding.articleList.visibility = View.GONE
            binding.articleList.adapter = adapterGrid
        }

        viewModelGrid.getDataQuery(id)

        viewModelGrid.items.observe(this, Observer {
            onShowDataGrid(it)
        })
    }


    private fun onShowDataGrid(list: ArrayList<DataItem>) {
        binding.articleList.visibility = View.VISIBLE
        adapterGrid = ArticleAdapter(list)
        binding.articleList.adapter = adapterGrid

        adapterGrid.setOnItemClickCallback(object : ArticleAdapter.OnItemClickCallback {
            override fun onClicked(data: DataItem) {
                val intent = Intent(this@KategoryActivity, DetailArticleActivity::class.java)
                intent.putExtra(DetailArticleActivity.EXTRA_DATA_DETAIL, data)
                startActivity(intent)
            }

        })
    }

    private fun onShowData(list: ArrayList<DataItemKat>) {
        adapter = KategoryAdapter(list, stateKategory)
        binding.kategoriList.adapter = adapter

        adapter.setOnItemClickCallback(object : KategoryAdapter.OnItemClickCallback {
            override fun onClicked(dataItemKat: DataItemKat) {
                onGetDataGrid(dataItemKat.id)
            }
        })
    }


}