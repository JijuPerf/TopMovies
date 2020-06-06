package com.movie.topmovies.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.util.LinkedHashSet

abstract class BaseRecyclerAdapter<T, TH : BaseViewHolderItem<T>> : RecyclerView.Adapter<TH>() {
    protected var dataList: ArrayList<T>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TH {
        val layoutInflater = LayoutInflater.from(parent.context)
        val item = createView(layoutInflater, parent, viewType)
        item.onCreated()
        return item
    }

    abstract fun createView(inflater: LayoutInflater, container: ViewGroup, viewType: Int): TH

    fun setData(data: List<T>) {
        val dataSet = LinkedHashSet(data)
        dataList = ArrayList(dataSet.toList())
    }

    override fun getItemCount() = dataList?.size ?: 0

    override fun onBindViewHolder(holder: TH, position: Int) {
        dataList?.let {
            holder.bind(it[position], position)

        }
    }
}

