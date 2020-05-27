package com.mocktest.listpage.view.homepage.viewholder

import coil.api.load
import com.mocktest.listpage.App
import com.mocktest.listpage.view.BaseViewHolderItem
import com.mocktest.listpage.R
import com.mocktest.listpage.databinding.PageListItemBinding
import com.mocktest.listpage.model.Content
import com.mocktest.listpage.utils.AppConstants

class HomePageViewHolder(private val mBinding: PageListItemBinding)
    : BaseViewHolderItem<Content>(mBinding.root) {

    override fun onCreated() {

    }

    override fun onBind(data: Content) {
        this.data = data

        data.name.let {
            mBinding.tvTitle.text = it
        }

        when (data.poster_image) {
            AppConstants.POSTER1 -> {
                mBinding.ivImagePreview.load(R.drawable.poster1, App.coilImageLoader){
                    crossfade(false)
                }
            }
            AppConstants.POSTER2 -> {
                mBinding.ivImagePreview.load(R.drawable.poster2, App.coilImageLoader){
                    crossfade(false)
                }
            }
            AppConstants.POSTER3 -> {
                mBinding.ivImagePreview.load(R.drawable.poster3, App.coilImageLoader){
                    crossfade(false)
                }
            }
            AppConstants.POSTER4 -> {
                mBinding.ivImagePreview.load(R.drawable.poster4, App.coilImageLoader){
                    crossfade(false)
                }
            }
            AppConstants.POSTER5 -> {
                mBinding.ivImagePreview.load(R.drawable.poster5, App.coilImageLoader){
                    crossfade(false)
                }
            }
            AppConstants.POSTER6 -> {
                mBinding.ivImagePreview.load(R.drawable.poster6, App.coilImageLoader){
                    crossfade(false)
                }
            }
            AppConstants.POSTER7 -> {
                mBinding.ivImagePreview.load(R.drawable.poster7, App.coilImageLoader){
                    crossfade(false)
                }
            }
            AppConstants.POSTER8 -> {
                mBinding.ivImagePreview.load(R.drawable.poster8, App.coilImageLoader){
                    crossfade(false)
                }
            }
            AppConstants.POSTER9 -> {
                mBinding.ivImagePreview.load(R.drawable.poster9, App.coilImageLoader){
                    crossfade(false)
                }
            }
            else -> {
                mBinding.ivImagePreview.load(R.drawable.placeholder_for_missing_posters, App.coilImageLoader){
                    crossfade(false)
                }
            }
        }
    }
}
