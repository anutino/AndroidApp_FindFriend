package com.afokeeva.findfriend.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.afokeeva.findfriend.data.Animal
import com.afokeeva.findfriend.network.NetworkService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AnimalsListViewModel : ViewModel(){
    private val animalInfoLiveData: MutableLiveData<List<Animal>> by lazy {
        MutableLiveData<List<Animal>>().also {
            loadUsers()
        }
    }

    fun getAnimalInfo(): LiveData<List<Animal>> {
        return animalInfoLiveData
    }

    private fun loadUsers() {
        // Do an asynchronous operation to fetch users.
        GlobalScope.launch(Dispatchers.IO) {
            // in main thread
            val animalInfo = NetworkService.instance.getAllAnimals()
            animalInfoLiveData.postValue(animalInfo)
        }
    }
}

//class ConcertViewModel(animalInfo: Animal) : ViewModel() {
//    val animalList: LiveData<PagedList<Animal>> =
//        animalInfo.description().toLiveData(pageSize = 50)
//}