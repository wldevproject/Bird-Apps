package com.cnd.birdapps.ui.view.article

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.cnd.birdapps.R
import com.cnd.birdapps.data.model.article.DataItem
import com.cnd.birdapps.data.model.MessageEvent
import com.cnd.birdapps.databinding.FragmentArticleBinding
import com.cnd.birdapps.ui.adapter.ArticleAdapter
import com.cnd.birdapps.ui.viewmodels.ArticleViewModel
import org.greenrobot.eventbus.EventBus
import kotlin.collections.ArrayList

class ArticleFragment : Fragment() {

    private lateinit var viewModel: ArticleViewModel
    private var _binding: FragmentArticleBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: ArticleAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        viewModel = ViewModelProvider(this).get(ArticleViewModel::class.java)
        _binding = FragmentArticleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity?)?.setSupportActionBar(binding.toolbar)
        EventBus.getDefault().postSticky(MessageEvent(true))
        initData()
    }

    private fun initData() {
        binding.apply {
            loading.loading.visibility = View.VISIBLE
            articleList.layoutManager = GridLayoutManager(requireContext().applicationContext, 2)
            articleList.adapter?.notifyDataSetChanged()
            articleList.scheduleLayoutAnimation()
            articleList.setHasFixedSize(true)
            ViewCompat.setNestedScrollingEnabled(articleList, true)
        }
        onGetData()
    }

    private fun onGetData() {
        viewModel.getData(true)

        viewModel.items.observe(viewLifecycleOwner, Observer {
            onShowData(it)
        })
    }

    private fun onShowData(listItems: ArrayList<DataItem>) {
        if (listItems.isNullOrEmpty()) {
            binding.noData.noData.visibility = View.VISIBLE
        }

        adapter = ArticleAdapter(listItems)
        binding.articleList.adapter = adapter

        Handler(Looper.getMainLooper()).postDelayed({
            if (_binding != null) {
                binding.loading.loading.visibility = View.GONE
            }
        }, 1000)

        adapter.setOnItemClickCallback(object : ArticleAdapter.OnItemClickCallback {
            override fun onClicked(data: DataItem) {
                val intent = Intent(requireContext(), DetailArticleActivity::class.java)
                intent.putExtra(DetailArticleActivity.EXTRA_DATA_DETAIL, data)
                startActivity(intent)
            }

            override fun onStatus(data: String) {
            }

        })
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
        val itemMenu = menu.findItem(R.id.action_search) as MenuItem
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

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        EventBus.getDefault().postSticky(MessageEvent(false))
    }

}