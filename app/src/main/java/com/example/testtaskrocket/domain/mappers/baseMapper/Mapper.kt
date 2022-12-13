package com.example.testtaskrocket.domain.mappers.baseMapper


interface Mapper<Model, Entity> {

    fun mapEntityFromModel(model: Model): Entity

    fun mapEntityToModel(entity: Entity): Model

}

fun <Model, Entity> List<Model>.mapListFrom(mapper: Mapper<Model, Entity>): List<Entity> = map { mapper.mapEntityFromModel(it) }

fun <Model, Entity> List<Entity>.mapListTo(mapper: Mapper<Model, Entity>): List<Model> = map { mapper.mapEntityToModel(it) }
