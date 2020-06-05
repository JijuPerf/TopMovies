package com.mocktest.listpage.view.homepage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.mocktest.listpage.databinding.FragmentMovieDetailsBinding
import com.mocktest.listpage.utils.setRxOnClickListener
import com.mocktest.listpage.view.BaseFragment
import com.mocktest.listpage.viewmodel.homepage.HomePageViewModel
import com.mocktest.listpage.viewobservers.homePageViewObserver.HomePageViewObserver
import kotlinx.android.synthetic.main.fragment_movie_details.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class MovieDetailsFragment : BaseFragment() {

    private var mViewObserver = HomePageViewObserver()
    private val mViewModel: HomePageViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        binding.data = mViewObserver
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setData()
        loadButtonClick()
    }

    private fun loadButtonClick() {
        llBackButton.setRxOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun setData() {
        mViewObserver.setMovieData(mViewModel.getMovieDetails()!!)
    }
}