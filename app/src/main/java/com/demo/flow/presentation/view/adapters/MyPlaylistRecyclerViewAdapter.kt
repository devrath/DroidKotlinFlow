package com.demo.flow.presentation.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.demo.flow.databinding.PlaylistItemBinding
import com.demo.flow.models.ApiUser

class MyPlaylistRecyclerViewAdapter(
    private val values: ArrayList<ApiUser>
) : RecyclerView.Adapter<MyPlaylistRecyclerViewAdapter.PlaylistViewHolder>() {

    override fun getItemCount() = values.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistViewHolder {
        return PlaylistViewHolder(PlaylistItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: PlaylistViewHolder, position: Int) {
        with(holder){
            with(values[position]) {
                binding.textViewUserName.text = name
                binding.textViewUserEmail.text = email
                Glide.with(binding.imageViewAvatar.context)
                    .load(avatar)
                    .into(binding.imageViewAvatar)

            }
        }
    }

    fun updateList(listItems: List<ApiUser>) {
        values.clear()
        values.addAll(listItems)
        notifyDataSetChanged()
    }

    inner class PlaylistViewHolder(val binding: PlaylistItemBinding) :RecyclerView.ViewHolder(binding.root)
}