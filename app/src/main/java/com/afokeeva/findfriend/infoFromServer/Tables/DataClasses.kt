package com.afokeeva.findfriend.infoFromServer.Tables

data class Animal (val id: Int, val age: Double, val name: String, val description: String, val type: Int, val img_url: String)
data class Image (val id: Int, val img_url: String, val id_animal: Int)
data class User (val id: Int, val name: String)
data class Favorites (val id_fav: Int, val id_animal: Int, val id_user: Int)

data class Test(var trackId: Int, var artistName: String, var artworkUrl30: String)

data class ListAnimals(val results: List<Animal>)
