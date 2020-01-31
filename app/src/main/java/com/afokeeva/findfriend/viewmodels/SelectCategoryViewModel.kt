package com.afokeeva.findfriend.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.afokeeva.findfriend.data.Category
import com.afokeeva.findfriend.network.NetworkService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SelectCategoryViewModel : ViewModel(){
    val categoryListLiveData = MutableLiveData<List<Category>>()

    init {
        GlobalScope.launch(Dispatchers.IO) {// in main thread
         val categoriesList = NetworkService.instance.getCategories()
         categoryListLiveData.postValue(categoriesList)
        }

    }

}