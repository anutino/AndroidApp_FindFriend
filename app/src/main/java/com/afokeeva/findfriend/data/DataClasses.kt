package com.afokeeva.findfriend.data

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose;

data class Animal (var id: Int, val age: Double, val name: String, val description: String, val type: Int, val img_url: String)
data class Image (val id: Int, val img_url: String, val id_animal: Int)
data class Images (val id: Int, val img_urls: List<String>, val id_animal: Int)
data class User (val id: Int, val name: String)
data class Favorites (val id_fav: Int, val id_animal: Int, val id_user: Int)

data class Test(var trackId: Int, var artistName: String, var artworkUrl30: String)

data class ListAnimals(val results: List<Animal>)

data class Category (val id: Int, val name : String, val img_url : String)
