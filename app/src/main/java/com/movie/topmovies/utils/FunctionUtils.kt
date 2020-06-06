package com.movie.topmovies.utils

import android.view.View
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit


fun View.setRxOnClickListener(function: () -> Unit): Disposable {
    return RxView.clicks(this)
        .throttleFirst(AppConstants.BUTTON_THROTTLE, TimeUnit.MILLISECONDS)
        .subscribe {
            function()
        }
}



