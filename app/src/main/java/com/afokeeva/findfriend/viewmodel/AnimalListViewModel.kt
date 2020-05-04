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

class AnimalListViewModel : ViewModel(){
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
           // val animalInfo = NetworkService.instance.getAllAnimals() TODO NEED!!!

            //for test
            val list = mutableListOf<Animal>()
            list.add(1, Animal (1, 1.0, "Iris", "Very good cat", 2, "http") )
            list.add(2, Animal (2, 2.0, "Silvia", "Very good cat", 2, "http") )
            list.add(3, Animal (3, 1.0, "Lenskii", "Very good cat", 2, "http") )
            list.add(4, Animal (4, 1.5, "Alica", "Very good cat", 2, "http") )
            list.add(5, Animal (5, 6.0, "Wolf", "Very good dog", 1, "http") )
            list.add(6, Animal (6, 8.0, "Bro", "Very good dog", 1, "http") )
            list.add(7, Animal (7, 3.0, "Klark", "Very good dog", 1, "http") )
            list.add(8, Animal (8, 2.0, "NewS", "Very good dog", 1, "http") )

            animalInfoLiveData.postValue(list)
        }
    }
}

//class ConcertViewModel(animalInfo: Animal) : ViewModel() {
//    val animalList: LiveData<PagedList<Animal>> =
//        animalInfo.description().toLiveData(pageSize = 50)
//}