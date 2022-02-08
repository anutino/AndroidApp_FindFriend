package com.findfriend.ui.animalfavorite

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.findfriend.data.Animal
import com.findfriend.data.ShortAnimalInfo
import com.findfriend.repository.AnimalRepository

class AnimalsFavoriteListViewModel : ViewModel() {

    private val mAnimalFavoriteListLiveMutable : MutableLiveData<List<ShortAnimalInfo>> = MutableLiveData()
    val resultLiveData = mAnimalFavoriteListLiveMutable

    private fun loadFavoriteList() {
        val animalsFavoritesList = AnimalRepository.repository.getFavoritesAnimals(mAnimalFavoriteListLiveMutable,"")
        mAnimalFavoriteListLiveMutable.postValue(animalsFavoritesList)
    }

}