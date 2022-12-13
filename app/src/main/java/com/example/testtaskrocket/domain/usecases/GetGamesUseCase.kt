package com.example.testtaskrocket.domain.usecases

import com.example.testtaskrocket.domain.entity.GameResultEntity
import com.example.testtaskrocket.domain.result.BaseResult

interface GetGamesUseCase {
    suspend fun getGames(name: String): BaseResult<GameResultEntity>
}