package com.findfriend.domain.interceptors

import com.findfriend.data.database.AnimalDao
import com.findfriend.data.database.ImageDao
import com.findfriend.domain.AnimalApi
import com.findfriend.domain.model.*
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class AnimalApiInterceptor @Inject constructor(
    private val animalApi: AnimalApi
) {
    fun getAnimalTypes(): Single<List<AnimalType>> {
        return animalApi.getAnimalTypes()
    }

    fun getAnimalShortInfoList(): Single<List<ShortAnimalInfo>> {
        return animalApi.getAnimalShortInfoList().doOnSuccess {}
    }

    fun getAnimalShortInfoListFilteredByType(type: Int): Single<List<ShortAnimalInfo>> {
        return animalApi.getAnimalShortInfoListFilteredByType(type).doOnSuccess {}
    }

    fun getAnimalShortInfoListFilteredByAgeAndType(minAge: Int,
                                                   maxAge: Int,
                                                   type: Int
    ): Single<List<ShortAnimalInfo>> {
        return animalApi.getAnimalShortInfoListFilteredByAgeAndType(minAge,
            maxAge,
            type).doOnSuccess {}
    }

    fun getAnimalShortInfoListFilteredByAge(minAge: Int,
                                            maxAge: Int
    ): Single<List<ShortAnimalInfo>> {
        return animalApi.getAnimalShortInfoListFilteredByAge(minAge,
            maxAge).doOnSuccess {}
    }

    fun getFavorites(userId: String): Single<List<ShortAnimalInfo>> {
        return animalApi.getFavorites(userId)
    }

    fun getDetailedInfo(animalId: Int): Single<AnimalDetailedInfo> {
        return animalApi.getDetailedInfo(animalId)
    }

    fun addImages(imageDao: ImageDao) {
        animalApi.addImages(imageDao)
    }

    fun addAnimal(animalDao: AnimalDao) {
        animalApi.addAnimal(animalDao)
    }
}


