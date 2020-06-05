package com.mocktest.listpage.viewmodel.homepage


import com.mocktest.listpage.model.Result
import com.mocktest.listpage.repository.HomeRepository
import com.mocktest.listpage.viewmodel.BaseViewModel

class HomePageViewModel(private val mHomeRepository: HomeRepository) : BaseViewModel(){


    private var mResult: Result? = null

    fun getData() = mHomeRepository.getData()

    fun setMovieDetails(data: Result?) {
        mResult = data
    }

    fun getMovieDetails()= mResult



}