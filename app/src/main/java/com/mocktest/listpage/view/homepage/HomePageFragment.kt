package com.mocktest.listpage.view.homepage

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.mocktest.listpage.R
import com.mocktest.listpage.utils.setRxOnClickListener
import com.mocktest.listpage.view.BaseFragment
import com.mocktest.listpage.databinding.FragmentHomePageBinding
import com.mocktest.listpage.utils.PagedStatus
import com.mocktest.listpage.viewmodel.homepage.HomePageViewModel
import com.mocktest.listpage.viewobservers.homePageViewObserver.HomePageViewObserver
import kotlinx.android.synthetic.main.appbar_layout.*
import kotlinx.android.synthetic.main.fragment_home_page.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.net.SocketTimeoutException
import java.net.UnknownHostException


class HomePageFragment : BaseFragment() {

    private var mViewObserver = HomePageViewObserver()
    private val mViewModel: HomePageViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHomePageBinding.inflate(inflater, container, false)
        binding.data = mViewObserver
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData()
        loadButtonFunctionality()
    }

    private fun loadData() {

        mViewModel.getData().observe(viewLifecycleOwner, Observer { response ->
            when (response.status) {
                PagedStatus.PAGE -> {
                    mViewObserver.setProgressVisibility(true)
                    response.pageList?.observe(viewLifecycleOwner, Observer { pagedList ->
                        mViewObserver.setAdapterDataList(pagedList)
                        mViewObserver.setDataVisibility(true)
                        mViewObserver.setNoDataVisibility(false)

                    })
                }
                PagedStatus.ERROR -> {
                    mViewObserver.setProgressVisibility(false)
                    /*mViewObserver.setDataVisibility(false)
                    mViewObserver.setNoDataVisibility(true)*/
                    response.exception?.let { appException ->
                        if (appException.cause is UnknownHostException || appException.cause is SocketTimeoutException) {
                            showSnackBar("No Internet Connection")
                        } else {

                            response.exception.getErrorReponse()?.message?.let{
                                showSnackBar("Some thing went wrong")
                            }
                        }
                    } ?: showSnackBar("Some thing went wrong")
                }
                PagedStatus.COMPLETED -> {
                    mViewObserver.setProgressVisibility(false)
                }
            }
        })
        mViewObserver.getMovieList().setMovieDetials { data ->
            mViewModel.setMovieDetails(data)
            findNavController().navigate(R.id.action_homeFragment_to_movieDetailFragment)
        }
    }

    private fun showSnackBar(msg: String) {
        val snackBar = Snackbar.make(clMainLayout, msg, Snackbar.LENGTH_INDEFINITE)
        val snackRootView = snackBar.view

        val snackActionView = snackRootView
            .findViewById<Button>(R.id.snackbar_action)

        snackActionView.setTextColor(Color.RED)
        snackBar.setAction("Retry") {
            loadData()
            snackBar.dismiss()
        }
        snackBar.show()

    }

    override fun onResume() {
        super.onResume()
    }

    /**
     * Function to handle back button click
     */

    private fun loadButtonFunctionality(){

        llBackButton.setRxOnClickListener {
            activity!!.finish()
        }

    }


}