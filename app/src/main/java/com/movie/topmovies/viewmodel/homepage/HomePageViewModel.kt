package com.movie.topmovies.viewmodel.homepage


import com.movie.topmovies.model.Result
import com.movie.topmovies.repository.HomeRepository
import com.movie.topmovies.viewmodel.BaseViewModel

class HomePageViewModel(private val mHomeRepository: HomeRepository) : BaseViewModel(){


    private var mResult: Result? = null

    fun getData() = mHomeRepository.getData()

    fun setMovieDetails(data: Result?) {
        mResult = data
    }

    fun getMovieDetails()= mResult



}