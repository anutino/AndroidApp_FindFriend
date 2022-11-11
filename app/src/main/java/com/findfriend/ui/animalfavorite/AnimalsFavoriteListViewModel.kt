package com.findfriend.ui.animalfavorite

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.findfriend.domain.interceptors.AnimalApiInterceptor
import com.findfriend.domain.model.ShortAnimalInfo
import javax.inject.Inject

class AnimalsFavoriteListViewModel @Inject constructor(
    private val animalApiInterceptor: AnimalApiInterceptor
) :
    ViewModel() {

    private val animalFavoriteListLiveMutable: MutableLiveData<List<ShortAnimalInfo>> =
        MutableLiveData()
    val resultLiveData = animalFavoriteListLiveMutable

    private fun loadFavoriteList(id: String) {
        animalApiInterceptor.getFavorites(id)
            .doOnSuccess { animalFavoriteListLiveMutable.postValue(it) }.subscribe()
    }

}