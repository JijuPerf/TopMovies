package com.mocktest.listpage.injection

import com.mocktest.listpage.repository.HomeRepository
import com.mocktest.listpage.repository.api.RestAPIEntity
import com.mocktest.listpage.repository.api.RestApiService
import com.mocktest.listpage.viewmodel.homepage.HomePageViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun appModule() = module {

    single { RestApiService().getRetrofitInstance() }
    single { RestApiService().create(get(), RestAPIEntity::class.java) }

    single { HomeRepository(get()) }

    viewModel { HomePageViewModel(get()) }

}