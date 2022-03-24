package com.findfriend.ui.animaldetailedinfo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.findfriend.domain.model.AnimalDetailedInfo
import com.findfriend.domain.model.ShortAnimalInfo
import com.findfriend.data.repository.AnimalRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class AnimalDetailedInfoViewModel @Inject constructor(animalRepository: AnimalRepository) :
    ViewModel() {
    var repository: AnimalRepository = animalRepository

    private val mDetailedInfoLiveMutable: MutableLiveData<AnimalDetailedInfo> = MutableLiveData()
    val resultLiveData: LiveData<AnimalDetailedInfo> = mDetailedInfoLiveMutable

    private val mSelectedAnimalDetailedInfo: MutableLiveData<ShortAnimalInfo> = MutableLiveData()
    val resultSelectedAnimalDetailedInfo: LiveData<ShortAnimalInfo> = mSelectedAnimalDetailedInfo

    fun loadAnimalDetailedInfo() {
        viewModelScope.launch {
            mDetailedInfoLiveMutable.postValue(repository.fetchAnimalDetailedInfo(repository.getItemIdSelected()))
        }
    }

    fun getNameSelectedItem(): String {
        var name = ""
        viewModelScope.launch {
            for (ShortAnimalInfo in repository.fetchAnimalShortInfoList()) {
                if (ShortAnimalInfo.id == repository.getItemIdSelected()) {
                    name = ShortAnimalInfo.name
                    return@launch
                 }
            }
        }
        return name
    }

    fun getAgeSelectedItem(): Double {
       var age : Double = 0.0
        viewModelScope.launch {
            for (ShortAnimalInfo in repository.fetchAnimalShortInfoList() ) {
                if (ShortAnimalInfo.id == repository.getItemIdSelected()) {
                    age = ShortAnimalInfo.age
                    return@launch
                }
            }
        }
        return age
    }
}
