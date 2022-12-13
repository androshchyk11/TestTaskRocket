package com.example.testtaskrocket.domain.entity

data class GameResultEntity(
    val games: List<GameEntity>,
    val offset: Int,
    val limit: Int
)