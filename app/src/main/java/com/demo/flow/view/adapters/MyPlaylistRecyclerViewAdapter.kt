package com.demo.flow.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.demo.flow.databinding.PlaylistItemBinding
import com.demo.flow.models.PlaylistItem

class MyPlaylistRecyclerViewAdapter () : RecyclerView.Adapter<MyPlaylistRecyclerViewAdapter.PlaylistViewHolder>() {

    private var values = ArrayList<PlaylistItem>()

    override fun getItemCount() = values.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistViewHolder {
        return PlaylistViewHolder(PlaylistItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: PlaylistViewHolder, position: Int) {
        with(holder){
            with(values[position]) {
                binding.playlistName.text = name
                binding.playlistCategory.text = category
            }
        }
    }

    fun updateList(listItems: List<PlaylistItem>) {
        values.clear()
        values.addAll(listItems)
        notifyDataSetChanged()
    }

    inner class PlaylistViewHolder(val binding: PlaylistItemBinding) :RecyclerView.ViewHolder(binding.root)
}