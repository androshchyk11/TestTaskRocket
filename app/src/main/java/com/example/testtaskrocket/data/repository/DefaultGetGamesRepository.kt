package com.example.testtaskrocket.data.repository

import com.example.testtaskrocket.data.api.ApiRequestService
import com.example.testtaskrocket.data.model.GamesResultModel
import com.example.testtaskrocket.domain.repositories.GetGamesRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultGetGamesRepository @Inject constructor(
    private val api: ApiRequestService
) : GetGamesRepository {
    override suspend fun getGames(name: String): GamesResultModel = api.getGames(name = name)
}