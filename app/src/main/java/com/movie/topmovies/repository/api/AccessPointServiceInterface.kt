package com.movie.topmovies.repository.api
   import com.movie.topmovies.model.PageListData
   import retrofit2.Call
import retrofit2.http.*


/**
 * Complete application API End-Point
 * Note some API have JSON Response, Some don't
 */

interface RestAPIEntity {


    /**
     * Most Recent Videos
     */
    @GET("movie/top_rated")
    fun getMostRecent(
        @Query("api_key") apiKey: String?,
        @Query("language") language: String?,
        @Query("page") page: Int?
    ): Call<PageListData>
}

