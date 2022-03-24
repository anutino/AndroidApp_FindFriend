package com.findfriend.data.networkservice

import com.findfriend.domain.model.AnimalDetailedInfo
import com.findfriend.domain.model.AnimalType
import com.findfriend.domain.model.ShortAnimalInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AnimalApi {

    @GET("/findfriend/animal-types")
    fun fetchAnimalTypes(): Call<List<AnimalType>>

    @GET("/findfriend/animal-short-info-list")
    fun fetchAnimalShortInfoList(): Call<List<ShortAnimalInfo>>

    @GET("/findfriend/animal-short-info-filtered-by-age")
    fun fetchAnimalShortInfoListFilteredByAge(
        @Query("from") minAge: String,
        @Query("to") maxAge: String
    ): Call<List<ShortAnimalInfo>>

    @GET("/findfriend/animal-short-info-filtered-by-type")
    fun fetchAnimalShortInfoListFilteredByType(@Query("type") type: Int): Call<List<ShortAnimalInfo>>

    @GET("/findfriend/animal-short-info-list-filtered-by-age-type")
    fun fetchAnimalShortInfoListFilteredByAgeAndType(
        @Query("from") minAge: String,
        @Query("to") maxAge: String,
        @Query("type") type: Int
    ): Call<List<ShortAnimalInfo>>

    @GET("/findfriend/animal-detailed-info")
    fun fetchDetailedInfo(@Query("animal_id") animalId: Int): Call<AnimalDetailedInfo>
//
//    @POST("/findfriend/create-user")
//    fun createUser(
//        @Query("name") name: String,
//        @Query("login") login: String,
//        @Query("password") password: String
//    ): Call<DescriptionMediaAnimalInfo>
//
//    @POST("/findfriend/delete-user")
//    fun deleteUserById(@Query("user_id") userId: Int): Call<DescriptionMediaAnimalInfo>
//
//    @POST("/findfriend/sing-in")
//    fun signIn(
//        @Query("login") login: String,
//        @Query("password") password: String
//    ): Call<DescriptionMediaAnimalInfo>
//
//
//    @POST("/findfriend/add-favorite-animal")
//    fun addFavoriteAnimal(
//        @Query("user_id") userId: String,
//        @Query("animal_id") animalId: String
//    ): Call<DescriptionMediaAnimalInfo>
//
//    @POST("/findfriend/delete-favorite-animal")
//    fun deleteFavoriteAnimal(
//        @Query("user_id") userId: String,
//        @Query("animal_id") animalId: String
//    ): Call<DescriptionMediaAnimalInfo>
//
    @POST("/findfriend/favorite-animals-list") //TODO
    fun fetchFavoriteAnimalList(@Query("user_id") userId: String): Call<List<ShortAnimalInfo>>

}