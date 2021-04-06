package com.findfriend.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.findfriend.data.AnimalType
import com.findfriend.network.NetworkService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AnimalTypeViewModel : ViewModel(){
//    val categoryListLiveData = MutableLiveData<List<Category>>()
//
//    init {
//        GlobalScope.launch(Dispatchers.IO) {// in main thread
//         val categoriesList = NetworkService.instance.getCategories()
//         categoryListLiveData.postValue(categoriesList)
//        }
//    }

    val categoryListLiveData: MutableLiveData<List<AnimalType>> by lazy {
        MutableLiveData<List<AnimalType>>().also {
            loadUsers()
        }
    }

    fun getAnimalInfo(): LiveData<List<AnimalType>> {
        return categoryListLiveData
    }

    private fun loadUsers() {
        // Do an asynchronous operation to fetch users.
        GlobalScope.launch(Dispatchers.IO) {
            // in main thread
            val categoriesList = NetworkService.instance.getAnimalTypes()
            categoryListLiveData.postValue(categoriesList)
        }
    }

}