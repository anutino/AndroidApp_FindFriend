package com.findfriend.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.findfriend.data.ShortAnimalInfo
import com.findfriend.repository.AnimalRepository
import com.findfriend.ui.adapter.AnimalListAdapter
import com.findfriend.ui.fragment.AnimalShortInfoListFragment

class AnimalShortInfoViewModel : ViewModel() {
    private var mAnimalInfoLiveData: MutableLiveData<List<ShortAnimalInfo>> = MutableLiveData()
    private lateinit var mShortAnimalInfoItem: ShortAnimalInfo

    fun loadAllAnimals() {
        AnimalRepository.repository.onFetchAnimalShortInfoList(mAnimalInfoLiveData)
        //for test
//            val list = mutableListOf<ShortAnimalInfo>()
////            list.add(0, Animal (0, 1.0, "Iris", "Very good cat", 2, "http", true) )
////            list.add(1, Animal (1, 1.0, "Iris", "Very good cat", 2, "http", false) )
////            list.add(2, Animal (2, 2.0, "Silvia", "Very good cat", 2, "http", false) )
////            list.add(3, Animal (3, 1.0, "Lenskii", "Very good cat", 2, "http", false) )
////            list.add(4, Animal (4, 1.5, "Alica", "Very good cat", 2, "http", false) )
////            list.add(5, Animal (5, 6.0, "Wolf", "Very good dog", 1, "http", false) )
////            list.add(6, Animal (6, 8.0, "Bro", "Very good dog", 1, "http", false) )
////            list.add(7, Animal (7, 3.0, "Klark", "Very good dog", 1, "http", false) )
////            list.add(8, Animal (8, 2.0, "NewS", "Very good dog", 1, "http", false) )
        val list = mutableListOf<ShortAnimalInfo>()
        list.add(0, ShortAnimalInfo(0, 1.0, "Iris", "cat", "http", true))
        list.add(1, ShortAnimalInfo(1, 1.0, "Iris", "Very good cat", "http", false))
        list.add(2, ShortAnimalInfo(2, 2.0, "Silvia", "Very good cat", "http", false))
        list.add(3, ShortAnimalInfo(3, 1.0, "Lenskii", "Very good cat", "http", false))
        list.add(4, ShortAnimalInfo(4, 1.5, "Alica", "Very good cat", "http", false))
        list.add(5, ShortAnimalInfo(5, 6.0, "Wolf", "Very good dog", "http", false))
        list.add(6, ShortAnimalInfo(6, 8.0, "Bro", "Very good dog", "http", false))
        list.add(7, ShortAnimalInfo(7, 3.0, "Klark", "Very good dog", "http", false))
        list.add(8, ShortAnimalInfo(8, 2.0, "NewS", "Very good dog", "http", false))
        mAnimalInfoLiveData.postValue(list)
    }

    fun loadAnimalListFilteredByType(type: Int) {
        AnimalRepository.repository.fetchAnimalShortInfoListFilteredByType(mAnimalInfoLiveData,
            type)
    }

    fun loadAnimalListFilteredByAgeAndType(minAge: String, maxAge: String, type: Int) {
        AnimalRepository.repository.onFetchAnimalsShortInfoListFilteredByAgeAndType(
            mAnimalInfoLiveData,
            minAge,
            maxAge,
            type)
    }

    fun loadAnimalListFilteredByAge(minAge: String, maxAge: String) {
        AnimalRepository.repository.onFetchAnimalShortListFilteredByAge(mAnimalInfoLiveData,
            minAge,
            maxAge)
    }

    fun loadAnimalFavoriteList() {
        AnimalRepository.repository.getFavoritesAnimals(mAnimalInfoLiveData, "1")
    }

    fun getAnimalInfo(): LiveData<List<ShortAnimalInfo>> {
        return mAnimalInfoLiveData
    }

    fun getItemAnimalInfo(): ShortAnimalInfo {
        return mShortAnimalInfoItem
    }

    fun setAnimalInfoById(id: Int) {
        mShortAnimalInfoItem = mAnimalInfoLiveData.value?.get(id)!!
        Log.d("t", "mShortAnimalInfoItem "+mShortAnimalInfoItem)

        //  mShortAnimalInfoItem = mAnimalInfoLiveData.value?.get(id) ?: ShortAnimalInfo(0,0.0,"","","",false)
    }
}
