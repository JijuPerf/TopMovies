package com.mocktest.listpage.view
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mocktest.listpage.App

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope


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

