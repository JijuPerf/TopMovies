package com.mocktest.listpage.view.homepage.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.mocktest.listpage.databinding.PageListItemBinding
import com.mocktest.listpage.model.Result
import com.mocktest.listpage.view.homepage.viewholder.HomePageViewHolder

class HomePageAdapter() :
    PagedListAdapter<Result, HomePageViewHolder>(diffCallback) {

    private  lateinit var  mMovieCallback:(Result) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomePageViewHolder {
        val binding = PageListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return HomePageViewHolder(binding, mMovieCallback)
    }

    fun setMovieDetials(callback: (Result) -> Unit) {
        mMovieCallback = callback
    }

    override fun onBindViewHolder(holder: HomePageViewHolder, position: Int) {
        getItem(position)?.let {
            holder.onBind(it)
        }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Result>() {
            override fun areContentsTheSame(
                oldItem: Result,
                newItem: Result
            ) = oldItem == newItem

            override fun areItemsTheSame(
                oldItem: Result,
                newItem: Result
            ) = oldItem.id == newItem.id

        }


    }
}