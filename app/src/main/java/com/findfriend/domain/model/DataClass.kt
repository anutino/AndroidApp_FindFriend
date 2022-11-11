package com.findfriend.domain.model

data class Animal(
    var id: Int,
    val age: Double,
    val name: String,
    val description: String,
    val type: Int,
    val mainPicture: String,
    var favorite: Boolean
)

data class ShortAnimalInfo(
    var id: Int,
    val age: Double,
    val name: String,
    val type: String,
    val mainPicture: String,
    val favorite: Boolean
)

data class DescriptionMediaAnimalInfo(val description: String, val mediaList: List<String>)

data class AnimalDetailedInfo(
    var id: Int,
    val age: Double,
    val name: String,
    val description: String,
    val type: Int,
    val mediaList: List<Int>,
    var favorite: Boolean
)

data class AnimalType(val id: Int, val name: String, val img_url: String)

data class DogImg(val url: String)
