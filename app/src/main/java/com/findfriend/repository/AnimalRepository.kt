package com.findfriend.repository

import androidx.lifecycle.MutableLiveData
import com.findfriend.data.Animal
import com.findfriend.data.AnimalDetailedInfo
import com.findfriend.data.AnimalType
import com.findfriend.data.ShortAnimalInfo

interface AnimalRepository {

    fun fetchAnimalShortInfoList(animalInfoLiveData: MutableLiveData<List<ShortAnimalInfo>>)
    fun fetchAnimalShortInfoListFilteredByType(
        animalInfoLiveData: MutableLiveData<List<ShortAnimalInfo>>, type: Int)

    fun fetchAnimalsShortInfoListFilteredByAgeAndType(
        animalInfoLiveData: MutableLiveData<List<ShortAnimalInfo>>,
        minAge: String,
        maxAge: String,
        type: Int)

    fun fetchAnimalShortListFilteredByAge(
        animalInfoLiveData: MutableLiveData<List<ShortAnimalInfo>>, minAge: String, maxAge: String)

    fun fetchAnimalDetailedInfo(animalInfoLiveData: MutableLiveData<AnimalDetailedInfo>,
                                animalId: Int)

    fun getFavoritesAnimals(animalInfoLiveData: MutableLiveData<List<ShortAnimalInfo>>,
                            animalId: String): List<ShortAnimalInfo>

    fun getAnimalTypes(): List<AnimalType>

    fun getAnimalInfoById(): List<Animal>
}