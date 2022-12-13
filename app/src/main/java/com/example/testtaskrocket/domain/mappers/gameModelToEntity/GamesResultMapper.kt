package com.example.testtaskrocket.domain.mappers.gameModelToEntity

import com.example.testtaskrocket.data.model.GamesResultModel
import com.example.testtaskrocket.domain.entity.GameResultEntity
import com.example.testtaskrocket.domain.mappers.baseMapper.Mapper
import com.example.testtaskrocket.domain.mappers.baseMapper.mapListFrom
import com.example.testtaskrocket.domain.mappers.baseMapper.mapListTo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GamesResultMapper @Inject constructor(
    private val gamesModelToEntityMapper: GamesModelToEntityMapper
) : Mapper<GamesResultModel, GameResultEntity> {
    override fun mapEntityFromModel(model: GamesResultModel): GameResultEntity = with(model) {
        GameResultEntity(
            offset = offset ?: 0,
            limit = limit ?: 100,
            games = gameModels?.mapListFrom(gamesModelToEntityMapper) ?: listOf()
        )
    }

    override fun mapEntityToModel(entity: GameResultEntity): GamesResultModel = with(entity) {
        GamesResultModel(
            offset = offset,
            limit = limit,
            gameModels = games.mapListTo(gamesModelToEntityMapper)
        )
    }
}