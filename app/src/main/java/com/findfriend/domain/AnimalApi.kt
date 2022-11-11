package com.findfriend.domain

import com.findfriend.data.database.AnimalDao
import com.findfriend.data.database.ImageDao
import com.findfriend.domain.model.AnimalDetailedInfo
import com.findfriend.domain.model.AnimalType
import com.findfriend.domain.model.DescriptionMediaAnimalInfo
import com.findfriend.domain.model.ShortAnimalInfo
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AnimalApi {

    @GET("/findfriend/animal-types")
    fun getAnimalTypes(): Single<List<AnimalType>>

    @GET("/findfriend/animal-short-info-list")
    fun getAnimalShortInfoList(): Single<List<ShortAnimalInfo>>

    @GET("/findfriend/animal-short-info-filtered-by-age")
    fun getAnimalShortInfoListFilteredByAge(
        @Query("from") minAge: Int,
        @Query("to") maxAge: Int
    ): Single<List<ShortAnimalInfo>>

    @GET("/findfriend/animal-short-info-filtered-by-type")
    fun getAnimalShortInfoListFilteredByType(@Query("type") type: Int): Single<List<ShortAnimalInfo>>

    @GET("/findfriend/animal-short-info-list-filtered-by-age-type")
    fun getAnimalShortInfoListFilteredByAgeAndType(
        @Query("from") minAge: Int,
        @Query("to") maxAge: Int,
        @Query("type") type: Int
    ): Single<List<ShortAnimalInfo>>

    @GET("/findfriend/animal-detailed-info")
    fun getDetailedInfo(@Query("animal_id") animalId: Int): Single<AnimalDetailedInfo>

    @POST("/findfriend/favorite-animals-list") //TODO
    fun getFavorites(@Query("user_id") userId: String): Single<List<ShortAnimalInfo>>

    @POST("/findfriend/add-favorite-animal")
    fun addFavoriteAnimal(
        @Query("user_id") userId: String,
        @Query("animal_id") animalId: String
    ): Single<DescriptionMediaAnimalInfo>

    @POST("/findfriend/delete-favorite-animal")
    fun deleteFavoriteAnimal(
        @Query("user_id") userId: String,
        @Query("animal_id") animalId: String
    ): Single<DescriptionMediaAnimalInfo>

    fun addImages(imageDao: ImageDao)

    fun addAnimal(animalDao: AnimalDao)
}