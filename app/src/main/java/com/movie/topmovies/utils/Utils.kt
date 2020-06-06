package com.movie.topmovies.utils

import android.graphics.Color
import android.view.View
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.movie.topmovies.R

object Utils {

    fun showSnackBarOnline(view: View, text: String) {
        val snackBar = Snackbar.make(view, text, Snackbar.LENGTH_LONG)

        snackBar.view.setBackgroundColor(ContextCompat.getColor(view.context, R.color.green))
        snackBar.show()
    }

    fun showSnackBarOffline(view: View, text: String) {
        val snackBar = Snackbar.make(view, text, Snackbar.LENGTH_LONG)
        snackBar.view.setBackgroundColor(ContextCompat.getColor(view.context, R.color.red))
        snackBar.show()
    }

}
