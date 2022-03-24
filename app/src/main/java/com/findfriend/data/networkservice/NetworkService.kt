package com.findfriend.data.networkservice;

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkService @Inject constructor()  {

     companion object {
        var mInstance = ApiFactory.placeholderApi
    }
    fun getInstance(): AnimalApi {
        if (mInstance == null) {
            mInstance = ApiFactory.placeholderApi
        }
        return mInstance
    }

}
