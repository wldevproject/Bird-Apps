package com.cnd.birdapps.ui.adapter

import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.cnd.birdapps.R
import com.cnd.birdapps.data.model.Mastering
import com.cnd.birdapps.databinding.ItemPlayMasteringBinding


/**
 ** Written by CND_Studio 16/03/2021 22.29.
 ** Author @JoeFachrizal
 ** Happy Code...
 **/
class MasteringAdapter(private var listData: ArrayList<Mastering>) :
    RecyclerView.Adapter<MasteringAdapter.ViewHolder>() {
    private var flag = true

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MasteringAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = ItemPlayMasteringBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: MasteringAdapter.ViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount(): Int = listData.size

    inner class ViewHolder(var binding: ItemPlayMasteringBinding) : RecyclerView.ViewHolder(
        binding.root
    ) {
        fun bind(dataItem: Mastering) {
            binding.apply {
                txtName.text = dataItem.name
                txtSinger.text = dataItem.singer

                ivPlay.setOnClickListener {
                    flag = if (flag){
                        onItemClickCallback.onPlay(dataItem.song)
                        ivPlay.setImageResource(R.drawable.ic_pause)
                        Toast.makeText(itemView.context, "play", Toast.LENGTH_SHORT).show()
                        false
                    } else{
                        onItemClickCallback.onPause(dataItem.song)
                        ivPlay.setImageResource(R.drawable.ic_play)
                        Toast.makeText(itemView.context, "pause", Toast.LENGTH_SHORT).show()
                        true
                    }
                }

                ivStop.setOnClickListener {
                    if (!flag) {
                        onItemClickCallback.onStop(dataItem.song)
                        flag = true
                    }
                    ivPlay.setImageResource(R.drawable.ic_play)
                    Toast.makeText(itemView.context, "stop", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    init {
        notifyDataSetChanged()
    }

    interface OnItemClickCallback {
        fun onPlay(song: Int)
        fun onPause(song: Int)
        fun onStop(song: Int)
    }
}