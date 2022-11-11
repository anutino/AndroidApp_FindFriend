package com.findfriend.ui.animaldetailedinfo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.findfriend.domain.interceptors.AnimalApiInterceptor
import com.findfriend.domain.model.AnimalDetailedInfo
import javax.inject.Inject

class AnimalDetailedInfoViewModel @Inject constructor(
    private val animalApiInterceptor: AnimalApiInterceptor
) : ViewModel() {

    private val mDetailedInfoLiveMutable: MutableLiveData<AnimalDetailedInfo> = MutableLiveData()
    val resultLiveData: LiveData<AnimalDetailedInfo> = mDetailedInfoLiveMutable

    fun getDetailedInfo(id: Int) {
        animalApiInterceptor.getDetailedInfo(id)
            .doOnSuccess { mDetailedInfoLiveMutable.postValue(it) }.subscribe()
    }

}
