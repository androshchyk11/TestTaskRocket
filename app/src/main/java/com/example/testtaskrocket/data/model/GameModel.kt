package com.example.testtaskrocket.data.model

import com.google.gson.annotations.SerializedName

data class GameModel(
    @SerializedName("api_detail_url")
    val apiDetailUrl: String? = null,
    @SerializedName("date_added")
    val dateAdded: String? = null,
    @SerializedName("date_last_updated")
    val dateLastUpdated: String? = null,
    @SerializedName("deck")
    val deck: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("guid")
    val guid: String? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("image")
    val image: Image? = null,
    @SerializedName("image_tags")
    val imageTags: List<ImageTag>? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("site_detail_url")
    val siteDetailUrl: String? = null
)