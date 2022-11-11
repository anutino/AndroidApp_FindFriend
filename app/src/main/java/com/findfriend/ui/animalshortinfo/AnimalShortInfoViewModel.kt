package com.findfriend.ui.animalshortinfo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.findfriend.data.Constants
import com.findfriend.domain.interceptors.AnimalApiInterceptor
import com.findfriend.domain.model.ShortAnimalInfo
import kotlinx.coroutines.launch
import javax.inject.Inject

class AnimalShortInfoViewModel @Inject constructor(
    private val animalApiInterceptor: AnimalApiInterceptor
) :
    ViewModel() {

    private val shortAnimalInfoLiveMutable = MutableLiveData<List<ShortAnimalInfo>>()
    val resultLive: LiveData<List<ShortAnimalInfo>> = shortAnimalInfoLiveMutable

    private val mutableSelectedItem = MutableLiveData<Int>()
    var selectedItem: LiveData<Int> = mutableSelectedItem

    private lateinit var shortAnimalInfoItem: ShortAnimalInfo

    fun loadAnimalList(type: Int) {
        when (type) {
            Constants.AnimalType.DOG.ordinal ->
                getAnimalShortInfoListFilteredByType(Constants.AnimalType.DOG.ordinal)
            Constants.AnimalType.CAT.ordinal ->
                getAnimalShortInfoListFilteredByType(Constants.AnimalType.CAT.ordinal)
            Constants.AnimalType.ALL.ordinal -> getAnimalShortInfoList()
        }
    }

     private fun getAnimalShortInfoList() {
        animalApiInterceptor.getAnimalShortInfoList().
        doOnSuccess { shortAnimalInfoLiveMutable.postValue(it) }.subscribe()
    }

     private fun getAnimalShortInfoListFilteredByType(type: Int) {
         animalApiInterceptor.getAnimalShortInfoListFilteredByType(type).doOnSuccess {
             shortAnimalInfoLiveMutable.postValue(it)
         }.subscribe()
    }

    fun getAnimalShortInfoListFilteredByAgeAndType(minAge: Int, maxAge: Int, type: Int) {
       animalApiInterceptor.getAnimalShortInfoListFilteredByAgeAndType( minAge,
           maxAge,
           type).doOnSuccess {
           shortAnimalInfoLiveMutable.postValue(it)
       }.subscribe()
    }

    fun getAnimalShortInfoListFilteredByAge(minAge: Int, maxAge: Int) {
        animalApiInterceptor.getAnimalShortInfoListFilteredByAge(
            minAge,
            maxAge).doOnSuccess {
            shortAnimalInfoLiveMutable.postValue(it)
        }.subscribe()
    }

    fun loadAnimalFavoriteList() {
        animalApiInterceptor.getFavorites("1").doOnSuccess {
            shortAnimalInfoLiveMutable.postValue(it)
        }.subscribe()
    }

    fun setAnimalInfoById(id: Int) {
        viewModelScope.launch {
            shortAnimalInfoItem = shortAnimalInfoLiveMutable.value?.get(id)!!
        }
    }

    fun setType(type: Int) {
        // repository.setType(type)
    }



}
