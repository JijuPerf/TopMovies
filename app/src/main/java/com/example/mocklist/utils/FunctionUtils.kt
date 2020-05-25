package com.example.mocklist.utils

import android.view.View
import android.widget.EditText
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit


fun View.setRxOnClickListener(function: () -> Unit): Disposable {
    return RxView.clicks(this)
        .throttleFirst(AppConstants.BUTTON_THROTTLE, TimeUnit.MILLISECONDS)
        .subscribe {
            function()
        }
}

fun EditText.setRxOnEditTextChangeAfter(function: (String) -> Unit): Disposable {
    return RxTextView.afterTextChangeEvents(this)
        .throttleLast(AppConstants.BUTTON_THROTTLE, TimeUnit.MILLISECONDS)
        .observeOn(AndroidSchedulers.mainThread())
        .skip(1)
        .subscribe {
            it.editable()?.let { editable ->
                function(editable.toString())
            }
        }
}



