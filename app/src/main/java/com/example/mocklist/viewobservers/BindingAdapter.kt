package com.example.mocklist.viewobservers

import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import coil.api.load
import com.example.mocklist.App
import com.example.mocklist.R
import timber.log.Timber



@BindingAdapter("app:imageResource")
fun setImageResource(imageView: ImageView, @DrawableRes resource: Int) {
    resource?.let { url ->
        imageView.load(url, App.coilImageLoader) {
            crossfade(false)
            error(R.drawable.placeholder_for_missing_posters)
            Timber.d("image url $url")
        }
    }
}


