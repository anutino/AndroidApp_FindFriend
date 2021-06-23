package com.findfriend.networkservice;

class NetworkService {

     companion object {
        var mInstance = ApiFactory.placeholderApi
    }
    fun getInstance(): AnimalApi {
//        if (mInstance == null) {
//            mInstance = ApiFactory.placeholderApi
//        }
        return mInstance
    }

}
