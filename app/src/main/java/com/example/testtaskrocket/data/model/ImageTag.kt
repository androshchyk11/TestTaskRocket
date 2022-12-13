package com.example.testtaskrocket.data.model

import com.google.gson.annotations.SerializedName

data class ImageTag(
    @SerializedName("api_detail_url")
    val apiDetailUrl: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("total")
    val total: Int? = null
)