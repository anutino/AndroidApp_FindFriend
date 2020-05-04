package com.afokeeva.findfriend.data

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource

class AnimalDataSourceFactory(var useSearchFragment: Boolean) : DataSource.Factory<Int, Animal>() {
    private val liveDataSource = MutableLiveData<PageKeyedDataSource<Int, Animal>>()
    var itemDataSource = AnimalDataSource(useSearchFragment)

    override fun create(): DataSource<Int, Animal> {
        liveDataSource.postValue(itemDataSource)
        return itemDataSource
    }

    fun getItemLiveDataSource(): MutableLiveData<PageKeyedDataSource<Int, Animal>> {
        return liveDataSource
    }
}