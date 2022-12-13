package com.example.testtaskrocket.domain.usecases

import com.example.testtaskrocket.data.repository.DefaultGetGamesRepository
import com.example.testtaskrocket.domain.entity.GameResultEntity
import com.example.testtaskrocket.domain.mappers.gameModelToEntity.GamesResultMapper
import com.example.testtaskrocket.domain.result.BaseResult
import com.example.testtaskrocket.domain.result.foldResult
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultGetGamesUseCase @Inject constructor(
    private val getActionsRepository: DefaultGetGamesRepository,
    private val gamesResultMapper: GamesResultMapper
) :
    GetGamesUseCase {
    override suspend fun getGames(name: String): BaseResult<GameResultEntity> =
        runCatching { gamesResultMapper.mapEntityFromModel(getActionsRepository.getGames(name)) }.foldResult()
}