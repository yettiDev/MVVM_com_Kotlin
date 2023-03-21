package com.example.mvvm_com_kotlin.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.mvvm_com_kotlin.R
import com.example.mvvm_com_kotlin.databinding.LiveRoomBinding
import com.example.mvvm_com_kotlin.model.Live

class LiveAdapater(private var ItemClicked:(Live)->Unit): RecyclerView.Adapter<ViewHolder>() {

    private var lives = mutableListOf<Live>()

    fun setLiveList(lives: List<Live>) {

        this.lives = lives.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val list= LiveRoomBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return MainViewHolder(list)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when(holder){
            is MainViewHolder ->{
                holder.bind(lives[position], ItemClicked )
            }
        }

    }

    override fun getItemCount(): Int {
        return lives.size
    }
}

class MainViewHolder(val binding: LiveRoomBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(live: Live, ItemClicked: (Live) -> Unit ) {

        binding.title.text = live.title
        binding.author.text = live.author

        val requestOptions = RequestOptions()
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)

        Glide.with(itemView.context)
            .applyDefaultRequestOptions(requestOptions)
            .load(live.thumbnailUrl)
            .into(binding.thumbnail)

        itemView.setOnClickListener{
            ItemClicked(live)
        }

    }

}