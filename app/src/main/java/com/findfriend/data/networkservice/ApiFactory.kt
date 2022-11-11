package com.findfriend.data.networkservice

import com.findfriend.data.AppConstants
import com.findfriend.data.DogCatApi
import com.findfriend.domain.AnimalApi

object ApiFactory{

    val placeholderApi : AnimalApi = RetrofitFactory.retrofit(AppConstants.BASE_URL)
        .create(AnimalApi::class.java)

    val dogApi : DogCatApi = RetrofitFactory.retrofitDog(AppConstants.DOG_API)
        .create(DogCatApi::class.java)

    val catApi : DogCatApi = RetrofitFactory.retrofitCat(AppConstants.CAT_API)
        .create(DogCatApi::class.java)
}