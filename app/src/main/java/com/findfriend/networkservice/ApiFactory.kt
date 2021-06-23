package com.findfriend.networkservice

object ApiFactory{

    val placeholderApi : AnimalApi = RetrofitFactory.retrofit(AppConstants.BASE_URL)
        .create(AnimalApi::class.java)
}