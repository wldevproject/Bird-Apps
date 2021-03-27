package com.cnd.birdapps.ui.view.article

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.cnd.birdapps.R
import com.cnd.birdapps.data.model.article.DataItem
import com.cnd.birdapps.databinding.ActivityListArticleBinding
import com.cnd.birdapps.ui.adapter.ArticleAdapter
import com.cnd.birdapps.ui.viewmodels.ArticleViewModel

class ListArticleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListArticleBinding
    private val viewModel: ArticleViewModel by viewModels()
    private lateinit var adapter: ArticleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        initData()
    }

    private fun initData() {
        with(binding) {
            articleList.layoutManager = GridLayoutManager(applicationContext, 2)
//            articleList.adapter?.notifyDataSetChanged()
            articleList.scheduleLayoutAnimation()
            articleList.setHasFixedSize(true)
            ViewCompat.setNestedScrollingEnabled(articleList, true)
        }
        onGetData()
    }

    private fun onGetData() {
        viewModel.setData()

        viewModel.items.observe(this, Observer {
            onShowData(it)
        })

        viewModel.failure.observe(this, Observer {
            Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
        })
    }

    private fun onShowData(listItems: ArrayList<DataItem>) {
        adapter = ArticleAdapter(listItems)
        binding.articleList.adapter = adapter
        adapter.setOnItemClickCallback(object : ArticleAdapter.OnItemClickCallback {
            override fun onClicked(data: DataItem) {
                val intent = Intent(this@ListArticleActivity, DetailArticleActivity::class.java)
                intent.putExtra(DetailArticleActivity.EXTRA_DATA_DETAIL, data)
                startActivity(intent)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        val itemMenu = menu?.findItem(R.id.action_search) as MenuItem
        val searchView = itemMenu.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                adapter.filter.filter(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    adapter.filter.filter(newText)
                }
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }
}