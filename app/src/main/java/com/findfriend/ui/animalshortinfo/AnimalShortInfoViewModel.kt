package com.findfriend.ui.animalshortinfo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.findfriend.data.Constants
import com.findfriend.data.networkservice.AppConstants
import com.findfriend.domain.model.ShortAnimalInfo
import com.findfriend.data.repository.AnimalRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class AnimalShortInfoViewModel @Inject constructor(animalRepository: AnimalRepository) :
    ViewModel() {

    private val repository = animalRepository
    private val mShortAnimalInfoLiveMutable = MutableLiveData<List<ShortAnimalInfo>>()
    val resultLive: LiveData<List<ShortAnimalInfo>> = mShortAnimalInfoLiveMutable

    private val mutableSelectedItem = MutableLiveData<Int>()
    var selectedItem: LiveData<Int> = mutableSelectedItem

    private lateinit var mShortAnimalInfoItem: ShortAnimalInfo

    fun loadAnimalList() {
        viewModelScope.launch {
            when (repository.getItemIdSelected()) {
                Constants.AnimalType.DOG.ordinal ->
                    loadAnimalListFilteredByType(Constants.AnimalType.DOG.ordinal)
                Constants.AnimalType.CAT.ordinal ->
                    loadAnimalListFilteredByType(Constants.AnimalType.CAT.ordinal)
                Constants.AnimalType.ALL.ordinal -> loadAllAnimals()
            }
        }
    }

    private fun loadAllAnimals() {
        //viewModelScope.launch {
        // mShortAnimalInfoLiveMutable.postValue(repository.fetchAnimalShortInfoList())
    //}
        mShortAnimalInfoLiveMutable.postValue(testData())
    }

    private fun testData(): List<ShortAnimalInfo> {
        Log.d("TAG", " testData ")
        val list = mutableListOf<ShortAnimalInfo>()
        list.add(0, ShortAnimalInfo(0, 1.0, "Iris",  "2", "dog1.jpg", false))
        list.add(1, ShortAnimalInfo(0, 1.0, "Iris",  "2", "dog2.jpg", true))
        list.add(2, ShortAnimalInfo(0, 1.0, "Iris",  "2", "dog3.jpg", true))
        list.add(3, ShortAnimalInfo(0, 1.0, "Iris",  "2", "dog4.jpg", true))
        list.add(4, ShortAnimalInfo(0, 1.0, "Iris",  "2", "dog5.jpg", true))
        list.add(5, ShortAnimalInfo(0, 1.0, "Iris",  "2", "dog6.jpg", true))
        list.add(6, ShortAnimalInfo(0, 1.0, "Iris",  "2", "dog7.jpg", true))
        list.add(7, ShortAnimalInfo(0, 1.0, "Iris",  "2", "dog8.jpg", true))
        list.add(8, ShortAnimalInfo(0, 1.0, "Iris",  "2", "cat1.jpg", true))
        list.add(9, ShortAnimalInfo(0, 1.0, "Iris",  "2", "cat2.jpg", true))
        list.add(10, ShortAnimalInfo(0, 1.0, "Iris",  "2", "cat3.jpg", true))
        return list
    }

    private fun loadAnimalListFilteredByType(type: Int) {
        viewModelScope.launch {
            mShortAnimalInfoLiveMutable.postValue(repository.fetchAnimalShortInfoListFilteredByType(
                type))
        }
    }

    fun loadAnimalListFilteredByAgeAndType(minAge: String, maxAge: String, type: Int) {
        viewModelScope.launch {
            mShortAnimalInfoLiveMutable.postValue(
                repository.fetchAnimalsShortInfoListFilteredByAgeAndType(
                minAge,
                maxAge,
                type))
        }
    }

    fun loadAnimalListFilteredByAge(minAge: String, maxAge: String) {
        viewModelScope.launch {
            mShortAnimalInfoLiveMutable.postValue(repository.fetchAnimalShortListFilteredByAge(
                minAge,
                maxAge))
        }
    }

    fun loadAnimalFavoriteList() {
        viewModelScope.launch {
            mShortAnimalInfoLiveMutable.postValue(repository.fetchFavoritesAnimals("1"))
        }
    }

    fun setAnimalInfoById(id: Int) {
        viewModelScope.launch {
            mShortAnimalInfoItem = mShortAnimalInfoLiveMutable.value?.get(id)!!
            //  mShortAnimalInfoItem = mAnimalInfoLiveData.value?.get(id) ?: ShortAnimalInfo(0,0.0,"","","",false)
        }
    }

    fun setSelectedItem(itemId: Int) {
        repository.setItemIdSelected(itemId)
    }

    fun setAge(from : Int, to : Int){
        repository.setAge(from, to)
    }

    fun setType(type : Int){
        repository.setType(type)
    }

}
