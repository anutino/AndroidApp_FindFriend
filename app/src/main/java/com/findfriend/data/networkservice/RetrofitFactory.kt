package com.findfriend.data.networkservice

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitFactory {

    private val mLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val mClient =
        OkHttpClient.Builder()
            .addInterceptor(mLoggingInterceptor)
            .build()

    fun retrofit(baseUrl: String): Retrofit = Retrofit.Builder()
        .client(mClient)
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun retrofitDog(baseUrl: String): Retrofit = Retrofit.Builder()
        .client(mClient)
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()

    fun retrofitCat(baseUrl: String): Retrofit = Retrofit.Builder()
        .client(mClient)
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()

}