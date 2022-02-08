package com.findfriend.ui.animaldetailedinfo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.findfriend.data.AnimalDetailedInfo
import com.findfriend.repository.AnimalRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AnimalDetailedInfoViewModel : ViewModel(){

    private val mDetailedInfoLiveMutable: MutableLiveData<AnimalDetailedInfo> = MutableLiveData()
    val resultLiveData: LiveData<AnimalDetailedInfo> = mDetailedInfoLiveMutable

    fun loadAnimalDetailedInfo(animalId : Int) {
        GlobalScope.launch(Dispatchers.IO) {
            AnimalRepository.repository.onFetchAnimalDetailedInfo(mDetailedInfoLiveMutable, animalId)
        }
    }
}
