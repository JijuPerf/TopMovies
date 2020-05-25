package com.example.mocklist.view.homepage.viewholder

import coil.api.load
import com.example.mocklist.App
import com.example.mocklist.view.BaseViewHolderItem
import com.example.mocklist.R
import com.example.mocklist.databinding.ImageListItemBinding
import com.example.mocklist.model.Content

class ImageListViewHolder(private val mBinding: ImageListItemBinding)
    : BaseViewHolderItem<Content>(mBinding.root) {

    override fun onCreated() {

    }

    override fun onBind(data: Content) {
        this.data = data
        data.name.let {
            mBinding.tvImgHeading.text = it
        }
        if (data.poster_image == "poster1.jpg") {
            mBinding.ivImg.load(R.drawable.poster1, App.coilImageLoader){
                crossfade(false)
            }
            //mBinding.ivImg.setImageResource(R.drawable.poster1)
        } else if (data.poster_image == "poster2.jpg") {
            mBinding.ivImg.load(R.drawable.poster2, App.coilImageLoader){
                crossfade(false)
            }
        } else if (data.poster_image == "poster3.jpg") {
            mBinding.ivImg.load(R.drawable.poster3, App.coilImageLoader){
                crossfade(false)
            }
        } else if (data.poster_image == "poster4.jpg") {
            mBinding.ivImg.load(R.drawable.poster4, App.coilImageLoader){
                crossfade(false)
            }
        } else if (data.poster_image == "poster5.jpg") {
            mBinding.ivImg.load(R.drawable.poster5, App.coilImageLoader){
                crossfade(false)
            }
        } else if (data.poster_image == "poster6.jpg") {
            mBinding.ivImg.load(R.drawable.poster6, App.coilImageLoader){
                crossfade(false)
            }
        } else if (data.poster_image == "poster7.jpg") {
            mBinding.ivImg.load(R.drawable.poster7, App.coilImageLoader){
                crossfade(false)
            }
        } else if (data.poster_image == "poster8.jpg") {
            mBinding.ivImg.load(R.drawable.poster8, App.coilImageLoader){
                crossfade(false)
            }
        } else if (data.poster_image == "poster9.jpg") {
            mBinding.ivImg.load(R.drawable.poster9, App.coilImageLoader){
                crossfade(false)
            }
        }else{
            mBinding.ivImg.load(R.drawable.placeholder_for_missing_posters, App.coilImageLoader){
                crossfade(false)
            }
        }
    }
}
