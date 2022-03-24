package com.findfriend.ui.mainmenu

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.findfriend.domain.model.AnimalType
import com.findfriend.data.repository.AnimalRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class AnimalTypeViewModel @Inject constructor(animalRepository: AnimalRepository) : ViewModel() {

    private val repository = animalRepository
    private val mCategoryList: MutableLiveData<List<AnimalType>> = MutableLiveData()
    val resultLiveDate = mCategoryList

    fun loadTypeList() {
        viewModelScope.launch {
            mCategoryList.postValue(repository.fetchAnimalTypes())
        }
    }

    fun setSelectedItem(id: Int) {
        repository.setItemIdSelected(id)
    }

}