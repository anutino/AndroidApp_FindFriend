package com.afokeeva.findfriend.data

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource

class AnimalDataSourceFactory(var useSearchFragment: Boolean) : DataSource.Factory<Integer, Animal>() {
    private val liveDataSource = MutableLiveData<PageKeyedDataSource<Integer, Animal>>()
    var itemDataSource = AnimalDataSource(useSearchFragment)

    override fun create(): DataSource<Integer, Animal> {
        liveDataSource.postValue(itemDataSource)
        return itemDataSource
    }

    fun getItemLiveDataSource(): MutableLiveData<PageKeyedDataSource<Integer, Animal>> {
        return liveDataSource
    }
}