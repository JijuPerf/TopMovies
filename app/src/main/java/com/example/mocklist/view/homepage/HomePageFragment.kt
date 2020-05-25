package com.example.mocklist.view.homepage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.mocklist.utils.setRxOnClickListener
import com.example.mocklist.utils.setRxOnEditTextChangeAfter
import com.example.mocklist.view.BaseFragment
import com.example.mocklist.R
import com.example.mocklist.databinding.FragmentHomePageBinding
import com.example.mocklist.utils.Utils
import com.example.mocklist.viewmodel.homepage.HomePageViewModel
import com.example.mocklist.viewobservers.homePageViewObserver.HomePageViewObserver
import kotlinx.android.synthetic.main.appbar_layout.*
import kotlinx.android.synthetic.main.fragment_home_page.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


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
        loadButtonFunctionality()
        loadData()
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }


    /**
     * Function to load the Data from json file using pagination
     */

    private fun loadData(){
        mViewModel.getDataList().observe(this, Observer { page ->
            mViewObserver.setImgList(page)
            mViewObserver.setDataVisibility(true)
            mViewObserver.setNoDataVisibility(false)
            tvAppBarHeading.text = mViewModel.getTitle()
        })
    }

    /**
     * Function to handle different button clicks
     */

    private fun loadButtonFunctionality(){
        ivSearchListIcon.setRxOnClickListener {
            llWhiteButton.visibility = View.GONE
            tvAppBarHeading.visibility = View.GONE
            etSearchListView.visibility = View.VISIBLE
            etSearchListView.requestFocus()
            ivSearchListIcon.visibility = View.GONE
            Utils.showKeyboard(activity!!)
        }
        etSearchListView.setRxOnEditTextChangeAfter {
            if (it.length >= 3){
                mViewModel.getDataListSearch(it, mViewObserver.getAdapterList()).observe(this, Observer { page ->
                    if (page.isNotEmpty()){
                        mViewObserver.setImgList(page)
                        mViewObserver.setDataVisibility(true)
                        mViewObserver.setNoDataVisibility(false)
                    }else{
                        mViewObserver.setDataVisibility(false)
                        mViewObserver.setNoDataVisibility(true)
                    }
                })
            }
            if (it.isEmpty()){
                loadData()
            }
        }

        etSearchListView.setOnTouchListener(OnTouchListener { v, event ->
            val DRAWABLE_LEFT = 0
            val DRAWABLE_TOP = 1
            val DRAWABLE_RIGHT = 2
            val DRAWABLE_BOTTOM = 3
            if (event.action == MotionEvent.ACTION_UP) {
                if (event.rawX >= etSearchListView.getRight() - etSearchListView.getCompoundDrawables()
                        .get(DRAWABLE_RIGHT).getBounds().width()
                ) {
                    // your action here
                    Utils.hideKeyboard(activity!!)
                    ivSearchListIcon.visibility = View.VISIBLE
                    llWhiteButton.visibility = View.VISIBLE
                    tvAppBarHeading.visibility = View.VISIBLE
                    etSearchListView.visibility = View.GONE
                    etSearchListView.text?.clear()
                    loadData()
                    return@OnTouchListener true
                }
            }
            false
        })

        llWhiteButton.setRxOnClickListener {
            activity!!.finish()
        }

    }


}