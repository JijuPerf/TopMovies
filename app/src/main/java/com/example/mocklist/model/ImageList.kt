package com.example.mocklist.model

import com.google.gson.annotations.SerializedName

data class ImagePageList(
    val page: Page
)

data class Page(
    @SerializedName("content-items")
    val content_items: ContentItems,
    @SerializedName("page-num")
    val page_num: String,
    @SerializedName("page-size")
    val page_size: String,
    val title: String,
    @SerializedName("total-content-items")
    val total_content_items: String
)

data class ContentItems(
    val content: ArrayList<Content>
)

data class Content(
    @SerializedName("name")
    val name: String,
    @SerializedName("poster-image")
    val poster_image: String
)