package com.findfriend.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.findfriend.data.AnimalDetailedInfo
import com.findfriend.network.NetworkService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AnimalDetailedInfoViewModel : ViewModel(){
    //    private val animalInfoLiveData: MutableLiveData<List<ShortAnimalInfo>> by lazy {
//        MutableLiveData<List<ShortAnimalInfo>>().also {
//            //loadUsers()
//        }
//    }
    private val mAnimalDetailedInfo: MutableLiveData<AnimalDetailedInfo> = MutableLiveData()

    fun getAnimalDetailedInfo(): LiveData<AnimalDetailedInfo> {
        return mAnimalDetailedInfo
    }

    fun loadAnimalDetailedInfo(animalId : Int) {
        GlobalScope.launch(Dispatchers.IO) {
            NetworkService.instance.onFetchAnimalDetailedInfo(mAnimalDetailedInfo, animalId)
        }
    }
}
