package com.findfriend.data

import com.findfriend.domain.model.*
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface DogCatApi {

    @GET("/woof.json")
    fun fetchDogImg(): Observable<DogImg>

    @GET("/meow")
    fun fetchCatImg(): Observable<DogImg>

}