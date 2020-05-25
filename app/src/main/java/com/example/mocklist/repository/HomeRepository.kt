package com.example.mocklist.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.example.mocklist.managers.SessionManager
import com.example.mocklist.model.Content
import com.example.mocklist.model.ImagePageList
import com.example.mocklist.utils.AppConstants
import com.google.gson.Gson
import java.io.BufferedReader

class HomeRepository (private val mContext: Context, private val mSessionManager: SessionManager){

    val itemSizeIn1 = 20
    val itemSizeIn2 = 20 + itemSizeIn1
    val itemSizeIn3 = 14 + itemSizeIn2
    var currentPage = 1

    /**
     * Function to load the Data from json using pagination
     */

    fun getDataListPaginated(): LiveData<PagedList<Content>> {
        val pageDataSource = object : PageKeyedDataSource<Int, Content>() {

            override fun loadInitial(
                params: LoadInitialParams<Int>,
                callback: LoadInitialCallback<Int, Content>
            ) {
                val inputStream = mContext.assets.open(AppConstants.FIRST_PAGE_LIST)
                val fileString = inputStream.bufferedReader().use(BufferedReader::readText)
                val imagePageList = Gson().fromJson(fileString, ImagePageList::class.java)
                currentPage = Integer.valueOf(imagePageList.page.page_num)
                mSessionManager.setSessionName(imagePageList.page.title)
                callback.onResult(imagePageList.page.content_items.content , 0, imagePageList.page.content_items.content.size, 0, imagePageList.page.content_items.content.size)
            }

            override fun loadAfter(
                params: LoadParams<Int>,
                callback: LoadCallback<Int, Content>
            ) {
                if(currentPage == 1){
                    val inputStream = mContext.assets.open(AppConstants.SECOND_PAGE_LIST)
                    val fileString = inputStream.bufferedReader().use(BufferedReader::readText)
                    val imagePageList = Gson().fromJson(fileString, ImagePageList::class.java)
                    currentPage = Integer.valueOf(imagePageList.page.page_num)
                    callback.onResult(imagePageList.page.content_items.content , itemSizeIn2)
                }else if (currentPage == 2) {
                    val inputStream = mContext.assets.open(AppConstants.LAST_PAGE_LIST)
                    val fileString = inputStream.bufferedReader().use(BufferedReader::readText)
                    val imagePageList = Gson().fromJson(fileString, ImagePageList::class.java)
                    currentPage = Integer.valueOf(imagePageList.page.page_num)
                    callback.onResult(imagePageList.page.content_items.content , itemSizeIn3)
                }else{

                }
            }

            override fun loadBefore(
                params: LoadParams<Int>,
                callback: LoadCallback<Int, Content>
            ) {
            }

        }
        return getListingPage<Content>(pageDataSource)
    }


    /**
     * Function to load the Data while search
     * @param search_query
     * @param data_list
     */

    fun getDataListPageSearch(
        query: String,
        adapterList: PagedList<Content>?
    ): LiveData<PagedList<Content>> {
        val pageDataSource = object : PageKeyedDataSource<Int, Content>() {

            override fun loadInitial(
                params: LoadInitialParams<Int>,
                callback: LoadInitialCallback<Int, Content>
            ) {
                val filterList = adapterList?.filter{
                    it.name.contains(query, true)
                }
                if (filterList?.isNotEmpty()!!)
                callback.onResult(filterList , 0, adapterList.size, 0, adapterList.size)
            }

            override fun loadAfter(
                params: LoadParams<Int>,
                callback: LoadCallback<Int, Content>
            ) {

            }

            override fun loadBefore(
                params: LoadParams<Int>,
                callback: LoadCallback<Int, Content>
            ) {
            }

        }
        return getListingPage<Content>(pageDataSource)
    }

    /**
     * fun will return  page option of the file
     */

    private fun <T> getListingPage(pageDataSource: DataSource<Int, T>): LiveData<PagedList<T>> {
        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(AppConstants.PAGINATION_PAGE_SIZE)
            .build()
        return LivePagedListBuilder(object :
            DataSource.Factory<Int, T>() {
            override fun create(): DataSource<Int, T> = pageDataSource
        }, pagedListConfig).build()
    }
}