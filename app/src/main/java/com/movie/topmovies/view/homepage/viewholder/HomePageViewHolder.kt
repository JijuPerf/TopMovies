package com.movie.topmovies.view.homepage.viewholder

import com.movie.topmovies.view.BaseViewHolderItem
import com.movie.topmovies.databinding.PageListItemBinding
import com.movie.topmovies.model.Result
import com.movie.topmovies.utils.AppConstants
import com.movie.topmovies.utils.setRxOnClickListener
import com.movie.topmovies.viewobservers.setImageResource

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
