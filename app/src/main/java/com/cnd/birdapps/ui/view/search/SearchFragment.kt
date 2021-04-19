package com.cnd.birdapps.ui.view.search

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.cnd.birdapps.R
import com.cnd.birdapps.data.model.article.DataItem
import com.cnd.birdapps.databinding.FragmentSearchBinding
import com.cnd.birdapps.ui.adapter.ArticleAdapter
import com.cnd.birdapps.ui.view.article.DetailArticleActivity
import com.cnd.birdapps.ui.viewmodels.SearchViewModel
import kotlin.collections.ArrayList


class SearchFragment : Fragment() {
    private lateinit var viewModel: SearchViewModel
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private var adapter: ArticleAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        viewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity?)?.setSupportActionBar(binding.toolbar)
        initData()
    }

    private fun initData() {
        with(binding) {
            loading.loading.visibility = View.VISIBLE
            articleList.layoutManager = GridLayoutManager(requireContext().applicationContext, 2)
//            articleList.adapter?.notifyDataSetChanged()
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

        Handler(Looper.getMainLooper()).postDelayed({
            if (_binding != null) {
                binding.loading.loading.visibility = View.GONE
            }
        }, 1000)

        adapter?.setOnItemClickCallback(object : ArticleAdapter.OnItemClickCallback {
            override fun onClicked(data: DataItem) {
                val intent = Intent(requireContext(), DetailArticleActivity::class.java)
                intent.putExtra(DetailArticleActivity.EXTRA_DATA_DETAIL, data)
                startActivity(intent)
            }

            override fun onStatus(data: String) {
                if (data.isBlank() or data.isEmpty()) {
                    binding.noData.noData.visibility = View.VISIBLE
                } else {
                    binding.noData.noData.visibility = View.GONE
                }
            }

        })
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
        val itemMenu = menu.findItem(R.id.action_search) as MenuItem
        val searchView = itemMenu.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    adapter?.filter?.filter(query)
                    binding.articleList.adapter = adapter
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}