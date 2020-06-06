package com.movie.topmovies.utils

import com.movie.topmovies.model.ResponseErrorBody


/**
 * API Exception class
 */
class AppException(t: Throwable?, private val responseErrorData: ResponseErrorBody?) : Exception(t){
    fun getErrorReponse() = responseErrorData
}
