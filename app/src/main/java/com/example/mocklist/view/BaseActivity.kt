package com.example.mocklist.view
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.mocklist.App
import com.example.mocklist.R

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import org.koin.androidx.viewmodel.ext.android.viewModel


abstract class BaseActivity : AppCompatActivity(), CoroutineScope by MainScope() {

    private lateinit var mApplication: App
    private  var mThemeCount:Int =0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mApplication = application as App

    }
     fun reCreate() {
       recreate()


    }


    override fun onResume() {
        super.onResume()

    }


    override fun onStart() {
        super.onStart()

    }
}

