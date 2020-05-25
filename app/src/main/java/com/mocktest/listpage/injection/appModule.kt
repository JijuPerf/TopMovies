package com.mocktest.listpage.injection

import com.mocktest.listpage.managers.SessionManager
import com.mocktest.listpage.repository.HomeRepository
import com.mocktest.listpage.viewmodel.homepage.HomePageViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun appModule() = module {

    single { SessionManager(androidApplication()) }

    single { HomeRepository(get(), get()) }

    viewModel { HomePageViewModel(get(), get()) }

}