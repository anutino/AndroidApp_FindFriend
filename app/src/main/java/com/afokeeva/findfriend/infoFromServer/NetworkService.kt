package com.afokeeva.findfriend.infoFromServer

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

open class NetworkService {
    private var BASE_URL = "http:localhost:8080/"
    private var retrofit: Retrofit

    constructor() {
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    companion object{
        var  instance =  NetworkService

        fun companionFun() : Companion {
            if(instance == null ){
                instance = NetworkService
            }
            return instance
        }

        fun getJSONApi(): JSONPlaceHolderApi {
            return NetworkService().retrofit.create(JSONPlaceHolderApi::class.java)
        }
    }
}