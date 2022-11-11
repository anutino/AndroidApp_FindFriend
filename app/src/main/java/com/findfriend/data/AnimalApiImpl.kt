package com.findfriend.data

import com.findfriend.data.database.AnimalDao
import com.findfriend.data.database.ImageDao
import com.findfriend.data.networkservice.NetworkService
import com.findfriend.data.testdatastorage.TestAnimalData
import com.findfriend.domain.AnimalApi
import com.findfriend.domain.model.*
import com.findfriend.utilsrx.composeIO
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class AnimalApiImpl @Inject constructor(
    private val networkService: NetworkService, private val testAnimalApi: TestAnimalData
) : AnimalApi {

    override fun getAnimalTypes(): Single<List<AnimalType>> {
        if (AppConstants.IS_REMOTE_DATABASE_USE) {
            return networkService.getInstance()
                .getAnimalTypes()
                .composeIO().doOnSuccess { }
        }
        return testAnimalApi.getAnimalTypes()
    }

    override fun getAnimalShortInfoList(): Single<List<ShortAnimalInfo>> {
        if (AppConstants.IS_REMOTE_DATABASE_USE) {
            return networkService.getInstance()
                .getAnimalShortInfoList()
                .composeIO()
        }
        return testAnimalApi.getAnimalShortInfoList()
    }

    override fun getAnimalShortInfoListFilteredByAge(minAge: Int,
                                                     maxAge: Int
    ): Single<List<ShortAnimalInfo>> {
        if (AppConstants.IS_REMOTE_DATABASE_USE) {
            return networkService.getInstance()
                .getAnimalShortInfoListFilteredByAge(minAge,
                    maxAge)
                .composeIO()
        }
        return testAnimalApi.getAnimalShortInfoListFilteredByAge(minAge,
            maxAge)
    }

    override fun getAnimalShortInfoListFilteredByType(type: Int): Single<List<ShortAnimalInfo>> {
        if (AppConstants.IS_REMOTE_DATABASE_USE) {
            return networkService.getInstance()
                .getAnimalShortInfoListFilteredByType(type)
                .composeIO()
        }
        return testAnimalApi.getAnimalShortInfoListFilteredByType()
    }

    override fun getAnimalShortInfoListFilteredByAgeAndType(minAge: Int,
                                                            maxAge: Int,
                                                            type: Int
    ): Single<List<ShortAnimalInfo>> {
        if (AppConstants.IS_REMOTE_DATABASE_USE) {
            networkService.getInstance()
                .getAnimalShortInfoListFilteredByAgeAndType(
                    minAge,
                    maxAge,
                    type)
                .composeIO()
        }
        return testAnimalApi.getAnimalShortInfoListFilteredByType()
    }

    override fun getDetailedInfo(animalId: Int): Single<AnimalDetailedInfo> {
        if (AppConstants.IS_REMOTE_DATABASE_USE) {
            return networkService.getInstance()
                .getDetailedInfo(animalId)
                .composeIO()
        }
        return testAnimalApi.getDetailedInfo()
    }

    override fun getFavorites(userId: String): Single<List<ShortAnimalInfo>> {
        return networkService.getInstance()
            .getFavorites("1").composeIO()
    }

    override fun addFavoriteAnimal(userId: String,
                                   animalId: String
    ): Single<DescriptionMediaAnimalInfo> {
        return networkService.getInstance()
            .addFavoriteAnimal(userId, animalId).composeIO()
    }

    override fun deleteFavoriteAnimal(userId: String,
                                      animalId: String
    ): Single<DescriptionMediaAnimalInfo> {
        return networkService.getInstance()
            .deleteFavoriteAnimal(userId, animalId).composeIO()
    }

    override fun addImages(imageDao: ImageDao) {
        imageDao.insertImage(testAnimalApi.getImages())
    }

    override fun addAnimal(animalDao: AnimalDao) {
        animalDao.insertAnimal(testAnimalApi.getAnimal())
    }

}