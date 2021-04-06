package com.findfriend.data

data class Animal(
    var id: Int,
    val age: Double,
    val name: String,
    val description: String,
    val type: Int,
    val img_url: String,
    var favorite: Boolean
)

data class ShortAnimalInfo(
    var id: Int,
    val age: Double,
    val name: String,
    val type: String,
    val mainPicture: String,
    var favorite: Boolean
)

//data class DescriptionMediaAnimalInfo(val description: String, val mediaList: List<String>)

data class AnimalDetailedInfo(
    var id: Int,
    val age: Double,
    val name: String,
    val description: String,
    val type: Int,
    val mediaList: List<String>,
    var favorite: Boolean
)

data class Image(val id: Int, val mediaList: String, val id_animal: Int)
data class Images(val id: Int, val mediaList: List<String>, val id_animal: Int)
data class User(val id: Int, val name: String)
data class AnimalFavoriteList(val id_fav: Int, val id_animal: Int, val id_user: Int)

data class Test(var trackId: Int, var artistName: String, var artworkUrl30: String)

data class ListAnimals(val results: List<Animal>)

data class AnimalType(val id: Int, val name: String, val img_url: String)
