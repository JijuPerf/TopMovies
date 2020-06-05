package com.mocktest.listpage.view.homepage.viewholder

import com.mocktest.listpage.view.BaseViewHolderItem
import com.mocktest.listpage.databinding.PageListItemBinding
import com.mocktest.listpage.model.Result
import com.mocktest.listpage.utils.AppConstants
import com.mocktest.listpage.utils.setRxOnClickListener
import com.mocktest.listpage.viewobservers.setImageResource

class HomePageViewHolder(
    private val mBinding: PageListItemBinding,
    private val mMovieDetails: (Result) -> Unit
    ) : BaseViewHolderItem<Result>(mBinding.root) {

    override fun onCreated() {
        itemView.setRxOnClickListener {
            data?.let {
                mMovieDetails(it)
            }

        }
    }

    override fun onBind(data: Result) {
        this.data = data

        data.title.let {
            mBinding.tvTitle.text = it
        }

        setImageResource(mBinding.ivImagePreview, AppConstants.IMG_BASE_URL+data.posterPath)
    }
}
