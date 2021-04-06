package com.findfriend.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.findfriend.data.ShortAnimalInfo
import com.findfriend.network.NetworkService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AnimalShortInfoViewModel : ViewModel() {

    private val mAnimalInfoLiveData: MutableLiveData<List<ShortAnimalInfo>> = MutableLiveData()

    fun getAnimalInfo(): LiveData<List<ShortAnimalInfo>> {
        return mAnimalInfoLiveData
    }

    fun loadAllAnimals() {
        GlobalScope.launch(Dispatchers.IO) {
            NetworkService.instance.onFetchAnimalShortInfoList(mAnimalInfoLiveData)
            //for test
//            val list = mutableListOf<ShortAnimalInfo>()
//            list.add(0, Animal (0, 1.0, "Iris", "Very good cat", 2, "http", true) )
//            list.add(1, Animal (1, 1.0, "Iris", "Very good cat", 2, "http", false) )
//            list.add(2, Animal (2, 2.0, "Silvia", "Very good cat", 2, "http", false) )
//            list.add(3, Animal (3, 1.0, "Lenskii", "Very good cat", 2, "http", false) )
//            list.add(4, Animal (4, 1.5, "Alica", "Very good cat", 2, "http", false) )
//            list.add(5, Animal (5, 6.0, "Wolf", "Very good dog", 1, "http", false) )
//            list.add(6, Animal (6, 8.0, "Bro", "Very good dog", 1, "http", false) )
//            list.add(7, Animal (7, 3.0, "Klark", "Very good dog", 1, "http", false) )
//            list.add(8, Animal (8, 2.0, "NewS", "Very good dog", 1, "http", false) )
            // val animalInfo = MainActivity().getNetworkService().getAllAnimals()
            //  animalInfoLiveData.postValue(animalInfo)
        }
    }

    fun loadAnimalListFilteredByType(type : Int) {
        GlobalScope.launch(Dispatchers.IO) {
            NetworkService.instance.fetchAnimalShortInfoListFilteredByType(mAnimalInfoLiveData, type)
        }
    }

    fun loadAnimalListFilteredByAgeAndType(minAge : String, maxAge : String, type: Int) {
        GlobalScope.launch(Dispatchers.IO) {
            NetworkService.instance.onFetchAnimalsShortInfoListFilteredByAgeAndType(mAnimalInfoLiveData, minAge, maxAge, type)
        }
    }

    fun loadAnimalListFilteredByAge(minAge : String, maxAge : String) {
        GlobalScope.launch(Dispatchers.IO) {
            NetworkService.instance.onFetchAnimalShortListFilteredByAge(mAnimalInfoLiveData, minAge, maxAge)
        }
    }

    fun loadAnimalFavoriteList() {
        GlobalScope.launch(Dispatchers.IO) {
            NetworkService.instance.getFavoritesAnimals(mAnimalInfoLiveData,"1" )
        }
    }
}
