package com.movie.topmovies.injection

import com.movie.topmovies.repository.HomeRepository
import com.movie.topmovies.repository.api.RestAPIEntity
import com.movie.topmovies.repository.api.RestApiService
import com.movie.topmovies.viewmodel.homepage.HomePageViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun appModule() = module {

    single { RestApiService().getRetrofitInstance() }
    single { RestApiService().create(get(), RestAPIEntity::class.java) }

    single { HomeRepository(get()) }

    viewModel { HomePageViewModel(get()) }

}