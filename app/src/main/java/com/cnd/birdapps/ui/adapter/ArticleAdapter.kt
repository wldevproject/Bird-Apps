package com.cnd.birdapps.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cnd.birdapps.data.model.article.DataItem
import com.cnd.birdapps.databinding.ItemArticleBinding
import java.util.*
import kotlin.collections.ArrayList

/**
 ** Written by CND_Studio 16/03/2021 22.29.
 ** Author @JoeFachrizal
 ** Happy Code...
 **/
class ArticleAdapter(private var listData: ArrayList<DataItem>) :
    RecyclerView.Adapter<ArticleAdapter.ViewHolder>(), Filterable {
    var filterList: ArrayList<DataItem> = arrayListOf()

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = ItemArticleBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleAdapter.ViewHolder, position: Int) {
        holder.bind(filterList[position])
    }

    override fun getItemCount(): Int = filterList.size

    inner class ViewHolder(var binding: ItemArticleBinding) : RecyclerView.ViewHolder(
        binding.root
    ) {
        fun bind(dataItem: DataItem) {
            binding.speciesName.text = dataItem.birdSpecies.name
            Glide.with(itemView.context)
                .load(dataItem.image)
                .into(binding.img)

            itemView.setOnClickListener {
                onItemClickCallback.onClicked(dataItem)
            }
        }
    }

    interface OnItemClickCallback {
        fun onClicked(data: DataItem)
    }

    override fun getFilter(): Filter = myFilter

    private var myFilter: Filter = object : Filter() {
        override fun performFiltering(charSequence: CharSequence): FilterResults {
            val charSearch = charSequence.toString()
            filterList = if (charSequence.isEmpty()) listData
            else {
                val resultList: ArrayList<DataItem> = arrayListOf()
                for (data in listData) {
                    if (data.birdSpecies.name.toLowerCase(Locale.ROOT)
                            .contains(charSearch.toLowerCase(Locale.ROOT))
                    ) {
                        resultList.add(data)
                    }
                }
                resultList
            }

            val filterResults = FilterResults()
            filterResults.values = filterList
            return filterResults
        }

        @Suppress("UNCHECKED_CAST")
        override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
//            listData.clear()
            filterList = filterResults.values as ArrayList<DataItem>
            notifyDataSetChanged()
        }
    }

    init {
        filterList = listData
    }
}