package com.cnd.birdapps.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cnd.birdapps.R
import com.cnd.birdapps.data.model.kategory.DataItemKat
import com.cnd.birdapps.databinding.ItemKategoryBinding
import com.cnd.birdapps.utils.ConsData.KAT_ANIS
import com.cnd.birdapps.utils.ConsData.KAT_BEO
import com.cnd.birdapps.utils.ConsData.KAT_JALAK
import com.cnd.birdapps.utils.ConsData.KAT_LOVEBIRD
import com.cnd.birdapps.utils.ConsData.KAT_MORE
import kotlin.collections.ArrayList


/**
 ** Written by CND_Studio 16/03/2021 22.29.
 ** Author @JoeFachrizal
 ** Happy Code...
 **/
class KategoryAdapter(
    private var listData: ArrayList<DataItemKat>,
    private var charSequence: CharSequence
) :
    RecyclerView.Adapter<KategoryAdapter.ViewHolder>() {


    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KategoryAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = ItemKategoryBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: KategoryAdapter.ViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount(): Int = listData.size

    inner class ViewHolder(var binding: ItemKategoryBinding) : RecyclerView.ViewHolder(
        binding.root
    ) {
        @SuppressLint("SetTextI18n")
        fun bind(dataItemKat: DataItemKat) {
            binding.apply {
                txtName.text = dataItemKat.name

                var resourceId = 0
                when (charSequence) {
                    KAT_ANIS -> resourceId = R.drawable.ic_anis
                    KAT_BEO -> resourceId = R.drawable.ic_beo
                    KAT_JALAK -> resourceId = R.drawable.ic_jalak
                    KAT_LOVEBIRD -> resourceId = R.drawable.ic_lovebird
                    KAT_MORE -> {
                        val nama = dataItemKat.name.take(3)
                        when (nama) {
                            "Ani" -> resourceId = R.drawable.ic_anis
                            "Beo" -> resourceId = R.drawable.ic_beo
                            "Jal" -> resourceId = R.drawable.ic_jalak
                            "Lov" -> resourceId = R.drawable.ic_lovebird
                        }
                    }
                }

                Glide.with(itemView.context)
                    .load(resourceId)
                    .into(binding.img)
            }
            itemView.setOnClickListener {
                onItemClickCallback.onClicked(dataItemKat)
            }
        }
    }

    interface OnItemClickCallback {
        fun onClicked(dataItemKat: DataItemKat)
    }

    init {
        notifyDataSetChanged()
    }
}