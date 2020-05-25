package com.example.mocklist.injection

import com.example.mocklist.managers.SessionManager
import com.example.mocklist.repository.HomeRepository
import com.example.mocklist.viewmodel.homepage.HomePageViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun appModule() = module {

    single { SessionManager(androidApplication()) }

    single { HomeRepository(get(), get()) }

    viewModel { HomePageViewModel(get(), get()) }

}