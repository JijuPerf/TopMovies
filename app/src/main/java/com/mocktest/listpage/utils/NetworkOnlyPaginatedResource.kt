package com.mocktest.listpage.utils

import android.os.Handler
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.mocktest.listpage.model.ResponseErrorBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

abstract class NetworkOnlyPaginatedResource<ApiResponseType, PagingListType> {

    private val mResult = MediatorLiveData<PagedResource<PagedList<PagingListType>,ApiResponseType>>()
    private var mSkipCount = 0


    private val mPageKeyedDataSource = object : PageKeyedDataSource<Int, PagingListType>() {
        override fun loadInitial(
            params: LoadInitialParams<Int>,
            callback: LoadInitialCallback<Int, PagingListType>
        ) {
            createCall(1, params.requestedLoadSize).enqueue(
                object : Callback<ApiResponseType> {
                    override fun onFailure(call: Call<ApiResponseType>, t: Throwable) {
                        mResult.postValue(PagedResource.error(AppException(t, null), null, 600))
                    }

                    override fun onResponse(
                        call: Call<ApiResponseType>,
                        response: Response<ApiResponseType>
                    ) {
                        if (response.isSuccessful && response.code() in 200..299) {
                            response.body()?.let {
                                val resultList = convertResponse(it)
                                callback.onResult(resultList, 0, 2)
                            } ?: let {
                                // empty
                                callback.onResult(emptyList(), 0, 2)
                            }
                            Handler().postDelayed({
                                mResult.postValue(
                                    PagedResource.complete(
                                        response.body()
                                    )
                                )
                            }, 500)

                        } else {
                            val error = IOException(response.message())
                            val errorBody =
                                ResponseErrorBody(response.code().toString(), response.message())
                            mResult.postValue(
                                PagedResource.error(
                                    AppException(error, errorBody),
                                    null,
                                    response.code()
                                )
                            )
                        }
                    }

                }
            )
            mSkipCount = 2
        }

        override fun loadAfter(
            params: LoadParams<Int>,
            callback: LoadCallback<Int, PagingListType>
        ) {
            createCall(mSkipCount, params.requestedLoadSize).enqueue(
                object : Callback<ApiResponseType> {
                    override fun onFailure(call: Call<ApiResponseType>, t: Throwable) {
                        mResult.postValue(PagedResource.error(AppException(t, null), null, 600))
                    }

                    override fun onResponse(
                        call: Call<ApiResponseType>,
                        response: Response<ApiResponseType>
                    ) {
                        if (response.isSuccessful && response.code() in 200..299) {
                            response.body()?.let {
                                val resultList = convertResponse(it)
                                saveResultAndReInit(resultList)
                                callback.onResult(resultList, params.key + 1)
                            } ?: let {
                                // empty
                                callback.onResult(emptyList(), params.key + 1)
                            }
                        } else {
                            val error = IOException(response.message())
                            val errorBody =
                                ResponseErrorBody(response.code().toString(), response.message())
                            mResult.postValue(
                                PagedResource.error(
                                    AppException(error, errorBody),
                                    null,
                                    response.code()
                                )
                            )
                        }
                    }

                }
            )
            mSkipCount = params.key + 1
        }

        override fun loadBefore(
            params: LoadParams<Int>,
            callback: LoadCallback<Int, PagingListType>
        ) {
            //DO NOTHING
        }

    }

    // returns a LiveData that represents the resource, implemented
    // in the base class.
    val asLiveData: LiveData<PagedResource<PagedList<PagingListType>,ApiResponseType>>
        get() = mResult

    @WorkerThread
    abstract fun createCall(skip: Int, limit: Int): Call<ApiResponseType>

    @WorkerThread
    abstract fun convertResponse(apiResponse: ApiResponseType): List<PagingListType>

    @WorkerThread
    abstract fun saveResultAndReInit(pageList: List<PagingListType>)

    abstract fun createPagedListConfig(): PagedList.Config

    init {
        mResult.postValue(PagedResource.page(getProjectListingPage()))
    }


    private fun getProjectListingPage(): LiveData<PagedList<PagingListType>> {
        val pagedListConfig = createPagedListConfig()
        val pagedList = LivePagedListBuilder<Int, PagingListType>(object :
            DataSource.Factory<Int, PagingListType>() {
            override fun create(): DataSource<Int, PagingListType> = mPageKeyedDataSource
        }, pagedListConfig).build()
        return pagedList
    }
}