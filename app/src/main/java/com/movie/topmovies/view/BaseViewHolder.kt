package com.movie.topmovies.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolderItem<T>(view: View) : RecyclerView.ViewHolder(view) {
    protected var data: T? = null
    private var currentPosition = 0


    init {
        onConstruct()
    }

    private fun onConstruct() {
        onCreated()
    }

    fun bind(data: T, position: Int) {
        this.data = data
        currentPosition = position
        onBind(data)
    }

    protected fun getCurrentPosition() = currentPosition

    abstract fun onCreated()

    abstract fun onBind(data: T)
}