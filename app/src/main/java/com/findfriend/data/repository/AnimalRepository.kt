package com.findfriend.data.repository

import com.findfriend.domain.model.Animal
import com.findfriend.domain.model.AnimalDetailedInfo
import com.findfriend.domain.model.AnimalType
import com.findfriend.domain.model.ShortAnimalInfo

interface AnimalRepository {

    fun fetchAnimalShortInfoList(): List<ShortAnimalInfo>
    fun fetchAnimalShortInfoListFilteredByType(type: Int): List<ShortAnimalInfo>

    fun fetchAnimalsShortInfoListFilteredByAgeAndType(
        minAge: String,
        maxAge: String,
        type: Int
    ): List<ShortAnimalInfo>

    fun fetchAnimalShortListFilteredByAge(minAge: String, maxAge: String): List<ShortAnimalInfo>

    fun fetchAnimalDetailedInfo(animalId: Int): AnimalDetailedInfo

    fun fetchFavoritesAnimals(animalId: String): List<ShortAnimalInfo>

    fun fetchAnimalTypes(): List<AnimalType>

    fun fetchAnimalInfoById(): List<Animal>

    fun setItemIdSelected(id: Int)
    fun getItemIdSelected(): Int

    fun setAge(from: Int, to: Int)
    fun getAgeFrom(): Int
    fun getAgeTo(): Int

    fun setType(type: Int)
    fun getType(): Int
}