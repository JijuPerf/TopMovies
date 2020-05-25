package com.example.mocklist.viewmodel.homepage

import androidx.paging.PagedList
import com.example.mocklist.managers.SessionManager
import com.example.mocklist.model.Content
import com.example.mocklist.repository.HomeRepository
import com.example.mocklist.viewmodel.BaseViewModel

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