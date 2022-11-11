package com.findfriend.domain

import com.findfriend.domain.model.DescriptionMediaAnimalInfo
import retrofit2.Call
import retrofit2.http.Query
import retrofit2.http.POST

interface UserApi {

    @POST("/findfriend/create-user")
    fun createUser(
        @Query("name") name: String,
        @Query("login") login: String,
        @Query("password") password: String
    ): Call<DescriptionMediaAnimalInfo>

    @POST("/findfriend/delete-user")
    fun deleteUserById(@Query("user_id") userId: Int): Call<DescriptionMediaAnimalInfo>

    @POST("/findfriend/sing-in")
    fun signIn(
        @Query("login") login: String,
        @Query("password") password: String
    ): Call<DescriptionMediaAnimalInfo>

}