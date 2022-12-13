package com.example.testtaskrocket.domain.mappers.gameModelToEntity

import com.example.testtaskrocket.data.model.GameModel
import com.example.testtaskrocket.data.model.Image
import com.example.testtaskrocket.domain.entity.GameEntity
import com.example.testtaskrocket.domain.mappers.baseMapper.Mapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GamesModelToEntityMapper @Inject constructor() : Mapper<GameModel, GameEntity> {
    override fun mapEntityFromModel(model: GameModel): GameEntity = with(model) {
        GameEntity(
            apiDetailUrl = apiDetailUrl ?: "",
            dateAdded = dateAdded ?: "",
            dateLastUpdated = dateLastUpdated ?: "",
            deck = deck ?: "",
            description = description ?: "",
            guid = guid ?: "",
            id = id ?: -1,
            image = image ?: Image(),
            imageTags = imageTags ?: listOf(),
            name = name ?: "",
            siteDetailUrl = siteDetailUrl ?: ""
        )
    }

    override fun mapEntityToModel(entity: GameEntity): GameModel = with(entity){
        GameModel(
            apiDetailUrl = apiDetailUrl,
            dateAdded = dateAdded ,
            dateLastUpdated = dateLastUpdated,
            deck = deck,
            description = description,
            guid = guid,
            id = id,
            image = image,
            imageTags = imageTags,
            name = name,
            siteDetailUrl = siteDetailUrl
        )
    }
}