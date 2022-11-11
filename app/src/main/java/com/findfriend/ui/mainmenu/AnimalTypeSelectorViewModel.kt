package com.findfriend.ui.mainmenu

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.findfriend.domain.interceptors.AnimalApiInterceptor
import com.findfriend.domain.model.AnimalType
import javax.inject.Inject

class AnimalTypeSelectorViewModel @Inject constructor(
    private val animalApiInterceptor: AnimalApiInterceptor
) : ViewModel() {

    private val types: MutableLiveData<List<AnimalType>> = MutableLiveData()
    val resultLiveDate = types

    fun loadTypes() {
        if (types.value == null) {
            animalApiInterceptor.getAnimalTypes().doOnSuccess { types.postValue(it) }
                .subscribe()
        }
    }

}
