package com.movie.topmovies.view.homepage

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.movie.topmovies.R
import com.movie.topmovies.utils.setRxOnClickListener
import com.movie.topmovies.view.BaseFragment
import com.movie.topmovies.databinding.FragmentHomePageBinding
import com.movie.topmovies.utils.PagedStatus
import com.movie.topmovies.utils.Utils
import com.movie.topmovies.utils.networkcheck.*
import com.movie.topmovies.viewmodel.homepage.HomePageViewModel
import com.movie.topmovies.viewobservers.homePageViewObserver.HomePageViewObserver
import kotlinx.android.synthetic.main.appbar_layout.*
import kotlinx.android.synthetic.main.fragment_home_page.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.net.SocketTimeoutException
import java.net.UnknownHostException


class HomePageFragment : BaseFragment(){

    private var mViewObserver = HomePageViewObserver()
    private val mViewModel: HomePageViewModel by sharedViewModel()
    private var previousSate = true

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

        savedInstanceState?.let {
            previousSate = it.getBoolean("LOST_CONNECTION")
        }

        loadData()
        loadButtonFunctionality()

        NetworkEvents.observe(viewLifecycleOwner, Observer {
            if (it is Event.ConnectivityEvent)
                handleConnectivityChange(it.state)
        })
    }

    override fun onResume() {
        super.onResume()
        handleConnectivityChange(NetworkStateHolder)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean("LOST_CONNECTION", previousSate)
        super.onSaveInstanceState(outState)
    }

    private fun handleConnectivityChange(networkState: NetworkState) {
        if (networkState.isConnected && !previousSate) {
            showSuccessSnackBar("You are Online !")
        }

        if (!networkState.isConnected && previousSate) {
            Utils.showSnackBarOffline(clMainLayout, "You are Offline !")
        }

        previousSate = networkState.isConnected
    }

    private fun loadData() {

        if (NetworkStateHolder.isConnected){
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
                        response.exception?.let { appException ->
                            if (appException.cause is UnknownHostException || appException.cause is SocketTimeoutException) {
                                showAlertSnackBar("No Internet Connection")
                            } else {

                                response.exception.getErrorReponse()?.message?.let{
                                    showWarningSnackBar("Some thing went wrong")
                                }
                            }
                        } ?: showWarningSnackBar("Some thing went wrong")
                    }
                    PagedStatus.COMPLETED -> {
                        mViewObserver.setProgressVisibility(false)
                    }
                }
            })
        }else{
            showAlertSnackBar("No Internet Connection")
        }

        mViewObserver.getMovieList().setMovieDetials { data ->
            mViewModel.setMovieDetails(data)
            findNavController().navigate(R.id.action_homeFragment_to_movieDetailFragment)
        }
    }

    private fun showWarningSnackBar(msg: String) {
        val snackBar = Snackbar.make(clMainLayout, msg, Snackbar.LENGTH_INDEFINITE)

        snackBar.view.setBackgroundColor(ContextCompat.getColor(activity!!,R.color.yellow))

        snackBar.setTextColor(Color.BLACK)
        snackBar.setActionTextColor(Color.BLACK)
        snackBar.setAction("Retry") {
            loadData()
            snackBar.dismiss()
        }
        snackBar.show()

    }

    private fun showAlertSnackBar(msg: String) {
        val snackBar = Snackbar.make(clMainLayout, msg, Snackbar.LENGTH_INDEFINITE)

        snackBar.view.setBackgroundColor(ContextCompat.getColor(activity!!,R.color.red))

        snackBar.setTextColor(Color.WHITE)
        snackBar.setActionTextColor(Color.WHITE)
        snackBar.show()

    }

    private fun showSuccessSnackBar(msg: String) {
        val snackBar = Snackbar.make(clMainLayout, msg, Snackbar.LENGTH_INDEFINITE)

        snackBar.view.setBackgroundColor(ContextCompat.getColor(activity!!,R.color.green))

        snackBar.setTextColor(Color.WHITE)
        snackBar.setActionTextColor(Color.WHITE)
        snackBar.setAction("Retry") {
            loadData()
            snackBar.dismiss()
        }
        snackBar.show()

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