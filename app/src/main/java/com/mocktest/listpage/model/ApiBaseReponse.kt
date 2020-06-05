package com.mocktest.listpage.model

data class ApiResponseBody<T>(
    val statusCode:Int?,
    val success: Boolean?,
    val message:String,
    val data:T?,
    val error : ResponseErrorBody?
)
