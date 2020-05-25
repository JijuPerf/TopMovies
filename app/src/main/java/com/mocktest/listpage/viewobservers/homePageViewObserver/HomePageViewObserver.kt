package com.mocktest.listpage.viewobservers.homePageViewObserver

import android.view.View
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import androidx.paging.PagedList
import com.mocktest.listpage.model.Content
import com.mocktest.listpage.view.homepage.adapter.RomanticPageAdapter

class HomePageViewObserver : BaseObservable()  {

    private var mRomanticPageAdapter = RomanticPageAdapter()
    private var mIsDataFound: Boolean = true
    private var mNoDataFound: Boolean = false

    @Bindable
    fun getImageList() =mRomanticPageAdapter

    @Bindable
    fun getDataVisibility() = if (mIsDataFound) View.VISIBLE else View.GONE

    @Bindable
    fun getNoDataVisibility() = if (mNoDataFound) View.VISIBLE else View.GONE

    /**
     * fun to load the adapter with data
     */

    fun setImgList(page: PagedList<Content>)
    {
        mRomanticPageAdapter.submitList(page)
        mRomanticPageAdapter.notifyDataSetChanged()
    }

    /**
     * fun to get data from adapter
     */

    fun getAdapterList(): PagedList<Content>? {
        return mRomanticPageAdapter.currentList
    }


    /**
     * fun to set adapter visibility when data
     */
    fun setDataVisibility(data: Boolean) {
        mIsDataFound = data
        notifyPropertyChanged(BR.dataVisibility)
    }


    /**
     * fun to set layout visibility when no data
     */
    fun setNoDataVisibility(data: Boolean) {
        mNoDataFound = data
        notifyPropertyChanged(BR.noDataVisibility)
    }
}