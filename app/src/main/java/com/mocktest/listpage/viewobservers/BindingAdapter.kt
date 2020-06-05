package com.mocktest.listpage.viewobservers

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.api.load
import com.mocktest.listpage.App
import com.mocktest.listpage.R



@BindingAdapter("app:imageResource")
fun setImageResource(imageView: ImageView, imageUrl: String?) {
    imageUrl.let { url ->
        imageView.load(url, App.coilImageLoader) {
            crossfade(false)
            error(R.drawable.placeholder_for_missing_posters)
        }
    }
}

@BindingAdapter("loadImageFromURL")
fun loadImagefromURL(imageView: ImageView, imageUrl: String?) {

    imageUrl?.let { url ->
        imageView.load(url,App.coilImageLoader){
            placeholder(R.drawable.placeholder_for_missing_posters  )
            error(R.drawable.placeholder_for_missing_posters)
            crossfade(false)
        }
    }
}


