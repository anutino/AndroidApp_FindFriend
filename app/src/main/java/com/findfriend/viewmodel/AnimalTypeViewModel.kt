package com.findfriend.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.findfriend.data.AnimalType
import com.findfriend.repository.AnimalRepository

class AnimalTypeViewModel : ViewModel(){

    val categoryListLiveData: MutableLiveData<List<AnimalType>> by lazy {
        MutableLiveData<List<AnimalType>>().also {
            loadUsers()
        }
    }

    fun getAnimalInfo(): LiveData<List<AnimalType>> {
        return categoryListLiveData
    }

    private fun loadUsers() {
              val categoriesList = AnimalRepository.repository.getAnimalTypes()
            categoryListLiveData.postValue(categoriesList)
        }

}