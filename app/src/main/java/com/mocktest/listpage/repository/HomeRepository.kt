package com.mocktest.listpage.repository


import androidx.paging.PagedList
import com.mocktest.listpage.utils.AppConstants
import com.mocktest.listpage.model.PageListData
import com.mocktest.listpage.model.Result
import com.mocktest.listpage.repository.api.RestAPIEntity
import com.mocktest.listpage.utils.NetworkOnlyPaginatedResource
import retrofit2.Call

class HomeRepository (private val mRestAPIEntity: RestAPIEntity){

    fun getData() =
        object : NetworkOnlyPaginatedResource<PageListData, Result>() {
            override fun createCall(skip: Int, limit: Int): Call<PageListData> {
                return mRestAPIEntity.getMostRecent("ec01f8c2eb6ac402f2ca026dc2d9b8fd","en_US", skip)
            }


            override fun convertResponse(apiResponse: PageListData): List<Result> {
                apiResponse.results.let {
                    return it
                }
            }

            override fun saveResultAndReInit(pageList: List<Result>) {

            }

            override fun createPagedListConfig(): PagedList.Config =
                PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setPageSize(AppConstants.PAGINATION_PAGE_SIZE)
                    .build()

        }.asLiveData
}