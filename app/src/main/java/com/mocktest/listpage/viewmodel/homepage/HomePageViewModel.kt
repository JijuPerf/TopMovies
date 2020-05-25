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
    fun getDataList() = mHomeRepository.getDataListPaginated()


    /**
     * Function to load the Data when searching
     */
    fun getDataListSearch(
        query: String,
        adapterList: PagedList<Content>?
    ) = mHomeRepository.getDataListPageSearch(query, adapterList)

    /**
     * Function to load the Data when searching
     */
    fun getTitle() = mSessionManger.getSessionName()

}