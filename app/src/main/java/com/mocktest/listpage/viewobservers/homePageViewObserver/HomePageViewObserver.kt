package com.mocktest.listpage.viewobservers.homePageViewObserver

import android.view.View
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import androidx.paging.PagedList
import com.mocktest.listpage.model.Result
import com.mocktest.listpage.utils.AppConstants
import com.mocktest.listpage.view.homepage.adapter.HomePageAdapter

class HomePageViewObserver : BaseObservable()  {

    private var mHomePageAdapter = HomePageAdapter()
    private var mIsDataFound: Boolean = true
    private var mNoDataFound: Boolean = false
    private var mProgressVisible: Boolean = false
    private var mResult: Result? = null

    @Bindable
    fun getMovieList() =mHomePageAdapter

    @Bindable
    fun getDataVisibility() = if (mIsDataFound) View.VISIBLE else View.GONE

    @Bindable
    fun getNoDataVisibility() = if (mNoDataFound) View.VISIBLE else View.GONE

    @Bindable
    fun getProgressVisibility() = if (mProgressVisible) View.VISIBLE else View.GONE

    @Bindable
    fun getMovieTitle() = mResult?.title

    @Bindable
    fun getPopularity() = mResult?.popularity.toString()

    @Bindable
    fun getMovePic() = AppConstants.IMG_BASE_URL + mResult?.posterPath

    @Bindable
    fun getVote() = mResult?.voteCount.toString()

    @Bindable
    fun getReleaseDate() = mResult?.releaseDate

    @Bindable
    fun getOverView() = mResult?.overview

    /**
     * fun to load the adapter with data
     */

    fun setAdapterDataList(page: PagedList<Result>)
    {
        mHomePageAdapter.submitList(page)
        mHomePageAdapter.notifyDataSetChanged()
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


    fun setProgressVisibility(data: Boolean) {
        mProgressVisible = data
        notifyPropertyChanged(BR.progressVisibility)
    }

    fun setMovieData(result: Result){
        mResult = result
    }
}