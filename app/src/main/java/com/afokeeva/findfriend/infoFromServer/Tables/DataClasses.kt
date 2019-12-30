package com.afokeeva.findfriend.infoFromServer.Tables

data class Animal (val id: Integer, val age: Integer, val name: String, val description: String, val type: Integer, val img_url: String)
data class Image (val id: Integer, val jpg_url: String, val id_animal: Integer)
data class User (val id: Integer, val name: String)
data class Favorites (val id_fav: Integer, val id_animal: Integer, val id_user: Integer)
