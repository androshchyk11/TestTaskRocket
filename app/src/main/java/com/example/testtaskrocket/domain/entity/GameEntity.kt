package com.example.testtaskrocket.domain.entity

import com.example.testtaskrocket.data.model.Image
import com.example.testtaskrocket.data.model.ImageTag

data class GameEntity(
    val apiDetailUrl: String,
    val dateAdded: String,
    val dateLastUpdated: String,
    val deck: String,
    val description: String,
    val guid: String,
    val id: Int,
    val image: Image,
    val imageTags: List<ImageTag>,
    val name: String,
    val siteDetailUrl: String
)