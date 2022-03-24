package com.findfriend.ui.animalfavorite

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.findfriend.domain.model.ShortAnimalInfo
import com.findfriend.data.repository.AnimalRepository
import javax.inject.Inject

class AnimalsFavoriteListViewModel @Inject constructor(animalRepository: AnimalRepository) : ViewModel() {

    private val repository = animalRepository
    private val mAnimalFavoriteListLiveMutable : MutableLiveData<List<ShortAnimalInfo>> = MutableLiveData()
    val resultLiveData = mAnimalFavoriteListLiveMutable

    private fun loadFavoriteList() {
        val animalsFavoritesList = repository.fetchFavoritesAnimals("")
        mAnimalFavoriteListLiveMutable.postValue(animalsFavoritesList)
    }

}