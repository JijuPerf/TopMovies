package com.mocktest.listpage.viewmodel.homepage

import androidx.paging.PagedList
import com.mocktest.listpage.managers.SessionManager
import com.mocktest.listpage.model.Content
import com.mocktest.listpage.repository.HomeRepository
import com.mocktest.listpage.viewmodel.BaseViewModel

class HomePageViewModel(private val mHomeRepository: HomeRepository, private val mSessionManger: SessionManager) : BaseViewModel(){

    /**
     * Function to load the Data from json file using pagination
     */
    fun getPagedDataList() = mHomeRepository.getPaginatedDataList()


    /**
     * Function to load the Data when searching
     */
    fun getSearchDataList(
        query: String,
        adapterList: PagedList<Content>?
    ) = mHomeRepository.getSearchData(query, adapterList)

    /**
     * Function to load the Data when searching
     */
    fun getTitle() = mSessionManger.getSessionName()

}