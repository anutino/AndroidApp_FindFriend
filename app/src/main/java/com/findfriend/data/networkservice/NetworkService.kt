package com.findfriend.data.networkservice;

import com.findfriend.data.DogCatApi
import com.findfriend.domain.AnimalApi
import com.findfriend.domain.model.DogImg
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class NetworkService @Inject constructor() {

    companion object {
        var mInstance = ApiFactory.placeholderApi
        var mDogInstance = ApiFactory.dogApi
        var mCatInstance = ApiFactory.catApi
    }

    fun getInstance(): AnimalApi {
        return mInstance
    }

    fun getDogInstance(): DogCatApi {
        return mDogInstance
    }

    fun getCatInstance(): DogCatApi {
        return mCatInstance
    }

    fun doInParallel(): Observable<DogImg> {
        return Observable.merge(
            mDogInstance.fetchDogImg(),
            mCatInstance.fetchCatImg()
        )
    }

}
