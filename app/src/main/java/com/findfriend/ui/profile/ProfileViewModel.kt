package com.findfriend.ui.profile

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.findfriend.data.database.AnimalDatabase
import com.findfriend.domain.interceptors.AnimalApiInterceptor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val animalApiInterceptor: AnimalApiInterceptor,
    private val  context: Application
): ViewModel() {
    fun addImages(){
        viewModelScope.launch ( Dispatchers.IO ) {
            val imageDao = AnimalDatabase.getInstance(context).imageDao()
            animalApiInterceptor.addImages(imageDao)
        }
    }

    fun addAnimal(){
        val animalDao = AnimalDatabase.getInstance(context).animalDao()
        animalApiInterceptor.addAnimal(animalDao)

    }

}