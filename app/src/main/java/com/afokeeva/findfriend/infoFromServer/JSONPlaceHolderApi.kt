package com.afokeeva.findfriend.infoFromServer

import com.afokeeva.findfriend.infoFromServer.Tables.Animal
import com.afokeeva.findfriend.infoFromServer.Tables.ListAnimal
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.*

interface JSONPlaceHolderApi {
    /**
     * RestApi for Animals:
     * findAllAnimals?
     * findAllAnimalsByType?type=1 //find dogs or cats
     * findAllAnimalsByAge?from=0&to=15
     * findAnimalsFilterAgeType?type=1&from=0&to=15
     *
     * findAnimalById?id=1
     * /addAnimal
     * /updateAnimal
     * /deleteAnimal
     */

    @GET("/findAllAnimals")
    fun findAllAnimals() : Call<List<Animal>>

    @GET("/findAllAnimalsByType")
    fun findAllAnimalsByType(@Query("type") id_type: Int) : Call<List<Animal>>

    @GET("/findAllAnimalsByAge")
    fun findAllAnimalsByAge(@Query("from") from: Float, @Query("to") to: Float) : Call<List<Animal>>

    @GET("/findAnimalsFilterAgeType")
    fun findAnimalsFilterAgeType(@Query("type") type: Int, @Query("from") from: Int, @Query("to") to: Int) : Call<List<Animal>>


    @GET("/findAnimalById")
    fun findAnimalById(@Query("id") id: Int) : Call<List<Animal>>

    @GET("/addAnimal")
    fun addAnimal() : Call<List<Animal>>

    @GET("/updateAnimal")
    fun updateAnimal() : Call<List<Animal>>

    @GET("/deleteAnimal")
    fun deleteAnimal() : Call<List<Animal>>


}