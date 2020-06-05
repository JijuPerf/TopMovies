package com.mocktest.listpage.utils

import androidx.lifecycle.LiveData

/**
 * Data Resource type
 */
class Resource<out T> private constructor(
    val status: NetworkStatus,
    val code: Int?,
    val body: T?,
    val exception: AppException?
) {

    companion object {

        fun <T> success(data: T?, code: Int): Resource<T> = Resource(NetworkStatus.SUCCESS, code, data, null)

        fun <T> error(exception: AppException?, data: T?, code: Int): Resource<T> =
            Resource(NetworkStatus.ERROR, code, data, exception)

        fun <T> loading(data: T?): Resource<T> = Resource(NetworkStatus.LOADING, null, data, null)
    }
}

/**
 * Data Resource type
 */
class PagedResource<T,TH> private constructor(
    val status: PagedStatus,
    val code: Int?,
    val pageList: LiveData<T>?,
    val result:TH?,
    val exception: AppException?
) {

    companion object {

        fun <T,TH> page(pageList: LiveData<T>?): PagedResource<T,TH> =
            PagedResource(PagedStatus.PAGE, null, pageList,null, null)

        fun <T,TH> error(
            exception: AppException?,
            pageList: LiveData<T>?,
            code: Int
        ): PagedResource<T,TH> =
            PagedResource(PagedStatus.ERROR, code, pageList, null,exception)

        fun <T,TH> complete(response: TH?): PagedResource<T,TH> =
            PagedResource(PagedStatus.COMPLETED, null, null,response, null)
    }
}