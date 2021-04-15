package com.cnd.birdapps.ui.view.kategory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.ViewCompat
import androidx.lifecycle.Observer
import com.cnd.birdapps.data.model.kategory.DataItem
import com.cnd.birdapps.databinding.ActivityKategoryBinding
import com.cnd.birdapps.ui.adapter.KategoryAdapter
import com.cnd.birdapps.ui.viewmodels.KategoryViewModel
import com.cnd.birdapps.utils.ConsData.stateKategory
import kotlinx.android.synthetic.main.activity_detail_article.*
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
            kategoriList.adapter?.notifyDataSetChanged()
            kategoriList.scheduleLayoutAnimation()
            kategoriList.setHasFixedSize(true)
            ViewCompat.setNestedScrollingEnabled(kategoriList, true)

            birdName.text = stateKategory
        }
        onGetData()
    }

    private fun onGetData() {
        viewModel.getData(stateKategory)

        viewModel.items.observe(this, Observer {
            onShowData(it)
        })
    }

    private fun onShowData(list: ArrayList<DataItem>) {
        adapter = KategoryAdapter(list, stateKategory)
        binding.kategoriList.adapter = adapter

        adapter.setOnItemClickCallback(object : KategoryAdapter.OnItemClickCallback {
            override fun onClicked(dataItem: DataItem) {
                Toast.makeText(
                    this@KategoryActivity,
                    "${dataItem.id}\n${dataItem.name}",
                    Toast.LENGTH_SHORT
                ).show()
            }

        })
    }
}