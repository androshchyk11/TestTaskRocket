package com.example.testtaskrocket.data.api

import com.example.testtaskrocket.data.model.GamesResultModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiRequestService {

    @GET("games/")
    suspend fun getGames(
        @Query("api_key") apiKey: String = "9d45436f87d3848d2bdcce810bacb6df57dfd134",
        @Query("filter") name: String,
        @Query("format") format: String = "json",
        @Query("limit") limit:Int = 10,
        @Query("offset") offset:Int = 0,
    ): GamesResultModel
}