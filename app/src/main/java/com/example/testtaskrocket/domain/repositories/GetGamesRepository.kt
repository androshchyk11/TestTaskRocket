package com.example.testtaskrocket.domain.repositories

import com.example.testtaskrocket.data.model.GamesResultModel

interface GetGamesRepository {
    suspend fun getGames(name: String): GamesResultModel
}