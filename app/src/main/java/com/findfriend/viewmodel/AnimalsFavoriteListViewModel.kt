package com.findfriend.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.findfriend.data.Animal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AnimalsFavoriteListViewModel : ViewModel(){
//    val animalsFavoritesListLiveData = MutableLiveData<List<Animal>>()
//
//    init {
//        GlobalScope.launch(Dispatchers.IO) {// in main thread
//            val animalsFavoritesList = NetworkService.instance.getFavoritesAnimals()
//            animalsFavoritesListLiveData.postValue(animalsFavoritesList)
//        }
//    }

    private val animalsFavoritesListLiveData: MutableLiveData<List<Animal>> by lazy {
        MutableLiveData<List<Animal>>().also {
            loadUsers()
        }
    }

    fun getAnimalInfo(): LiveData<List<Animal>> {
        return animalsFavoritesListLiveData
    }

    private fun loadUsers() {
           // val animalsFavoritesList =  AnimalRepository.repository.getFavoritesAnimals()
          //  animalsFavoritesListLiveData.postValue(animalsFavoritesList)
    }

}