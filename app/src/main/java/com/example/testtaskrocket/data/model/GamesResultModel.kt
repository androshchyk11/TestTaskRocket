package com.example.testtaskrocket.data.model

import com.google.gson.annotations.SerializedName

data class GamesResultModel(
    @SerializedName("error")
    val error: String? = null,
    @SerializedName("limit")
    val limit: Int? = null,
    @SerializedName("number_of_page_results")
    val numberOfPageResults: Int? = null,
    @SerializedName("number_of_total_results")
    val numberOfTotalResults: Int? = null,
    @SerializedName("offset")
    val offset: Int? = null,
    @SerializedName("results")
    val gameModels: List<GameModel>? = null,
    @SerializedName("statusCode")
    val status_code: Int? = null,
    @SerializedName("version")
    val version: String? = null
)