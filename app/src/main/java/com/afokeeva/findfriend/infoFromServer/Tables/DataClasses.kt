package com.afokeeva.findfriend.infoFromServer.Tables

data class Animal (val id: Int, val age: Int, val name: String, val description: String, val type: Int, val img_url: String)
data class Image (val id: Int, val jpg_url: String, val id_animal: Int)
data class User (val id: Int, val name: String)
data class Favorites (val id_fav: Int, val id_animal: Int, val id_user: Int)

data class ListAnimal(val results: List<Animal>)
