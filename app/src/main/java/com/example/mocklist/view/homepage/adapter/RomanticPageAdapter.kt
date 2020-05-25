package com.example.mocklist.view.homepage.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.mocklist.databinding.ImageListItemBinding
import com.example.mocklist.model.Content
import com.example.mocklist.view.homepage.viewholder.ImageListViewHolder

class RomanticPageAdapter() :
    PagedListAdapter<Content, ImageListViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageListViewHolder {
        val binding = ImageListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ImageListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageListViewHolder, position: Int) {
        getItem(position)?.let {
            holder.onBind(it)
        }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Content>() {
            override fun areContentsTheSame(
                oldItem: Content,
                newItem: Content
            ) = oldItem == newItem

            override fun areItemsTheSame(
                oldItem: Content,
                newItem: Content
            ) = oldItem.name == newItem.name

        }


    }
}