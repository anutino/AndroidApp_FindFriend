package com.afokeeva.findfriend.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.afokeeva.findfriend.data.Animal
import com.afokeeva.findfriend.network.NetworkService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AnimalsFavoritesListViewModel : ViewModel(){
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
        // Do an asynchronous operation to fetch users.
        GlobalScope.launch(Dispatchers.IO) {
            // in main thread
            val animalsFavoritesList = NetworkService.instance.getFavoritesAnimals()
            animalsFavoritesListLiveData.postValue(animalsFavoritesList)
        }
    }

}