package com.findfriend.ui.mainmenu

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.findfriend.data.AnimalType
import com.findfriend.repository.AnimalRepository

class AnimalTypeViewModel(animalRepository: AnimalRepository) : ViewModel() {

    private val repository = animalRepository
    private val mCategoryListLiveMutable: MutableLiveData<List<AnimalType>> = MutableLiveData()
    val resultLiveDate = mCategoryListLiveMutable

    private fun loadCategoryList() {
        val categoriesList = repository.getAnimalTypes()
        mCategoryListLiveMutable.postValue(categoriesList)
    }

}