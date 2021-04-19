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
import com.cnd.birdapps.data.model.kategory.DataItemKat
import com.cnd.birdapps.databinding.ActivityKategoryBinding
import com.cnd.birdapps.ui.adapter.KategoryAdapter
import com.cnd.birdapps.ui.viewmodels.KategoryViewModel
import com.cnd.birdapps.utils.ConsData.stateKategory
import java.util.ArrayList

class KategoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityKategoryBinding
    private val viewModel: KategoryViewModel by viewModels()
    private lateinit var adapter: KategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initData()
    }

    private fun initData() {
        binding.apply {
            loading.loading.visibility = View.VISIBLE
            birdName.text = stateKategory

            kategoriList.adapter?.notifyDataSetChanged()
            kategoriList.scheduleLayoutAnimation()
            kategoriList.setHasFixedSize(true)
            ViewCompat.setNestedScrollingEnabled(kategoriList, true)
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

    private fun onShowData(list: ArrayList<DataItemKat>) {
        adapter = KategoryAdapter(list, stateKategory)
        binding.kategoriList.adapter = adapter

        Handler(Looper.getMainLooper()).postDelayed({
            binding.loading.loading.visibility = View.GONE
        }, 1000)

        adapter.setOnItemClickCallback(object : KategoryAdapter.OnItemClickCallback {
            override fun onClicked(dataItemKat: DataItemKat) {
                val intent = Intent(this@KategoryActivity, ListKategoriActivity::class.java)
                intent.putExtra(ListKategoriActivity.EXTRA_ID_KATEGORI, dataItemKat)
                startActivity(intent)
            }
        })
    }


}