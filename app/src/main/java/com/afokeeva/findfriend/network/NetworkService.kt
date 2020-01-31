package com.afokeeva.findfriend.network

import com.afokeeva.findfriend.data.Category
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

open class NetworkService {
    private var BASE_URL = "https://itunes.apple.com/" //"http:localhost:8080/"
    private var retrofit: Retrofit

   private constructor() {
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    companion object{
        var  instance = NetworkService()

    }



    fun getJSONApi(): JSONPlaceHolderApi {
        return NetworkService()
            .retrofit.create(JSONPlaceHolderApi::class.java)
    }

    suspend fun getCategories() : List<Category> {
        var list = mutableListOf<Category>()
        list.add(Category(1, " ", "http"))
        list.add(Category(1, " ", "http"))
        list.add(Category(1, " ", "http"))
        list.add(Category(1, " ", "http"))
        return list

    }
}