package com.mocktest.listpage.utils

import com.mocktest.listpage.model.ResponseErrorBody


/**
 * API Exception class
 */
class AppException(t: Throwable?, private val responseErrorData: ResponseErrorBody?) : Exception(t){
    fun getErrorReponse() = responseErrorData
}
