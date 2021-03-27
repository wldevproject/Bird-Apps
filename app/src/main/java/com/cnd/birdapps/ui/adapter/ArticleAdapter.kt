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
    private val userListFull: ArrayList<DataItem> = arrayListOf()

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
        holder.bind(listData[position])
    }

    override fun getItemCount(): Int = listData.size

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

    @Suppress("UNCHECKED_CAST")
    private var myFilter: Filter = object : Filter() {
        override fun performFiltering(charSequence: CharSequence): FilterResults {
            val filteredList: java.util.ArrayList<DataItem> = arrayListOf()
            if (charSequence.isEmpty()) {
                filteredList.addAll(userListFull)
            } else {
                for (data in userListFull) {
                    if (data.birdSpecies.name.toLowerCase(Locale.getDefault())
                            .contains(charSequence.toString().toLowerCase(Locale.getDefault()))
                    ) {
                        filteredList.add(data)
                    }
                }
            }
            val filterResults = FilterResults()
            filterResults.values = filteredList
            return filterResults
        }

        //Automatic on UI thread
        override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
            listData.clear()
            listData.addAll((filterResults.values as Collection<DataItem>))
            notifyDataSetChanged()
        }
    }

    init {
        userListFull.addAll(listData)
        notifyDataSetChanged()
    }
}