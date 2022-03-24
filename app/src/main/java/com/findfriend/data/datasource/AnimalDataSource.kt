package com.findfriend.data.datasource

import android.util.Log
import androidx.constraintlayout.widget.Constraints
import androidx.paging.PageKeyedDataSource
import com.findfriend.domain.model.Animal

class AnimalDataSource(var useSearchingAnimalFragment: Boolean) : PageKeyedDataSource<Int, Animal>() {
    val PAGE_SIZE = 50;
    val FIRST_PAGE = 9;
    var initialParams: LoadInitialParams<Int>? = null

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Animal>
    ) {
        val i = 1
        if (useSearchingAnimalFragment) {
            //TODO load for search fragment
        } else {
            //TODO load for favorite
        }
        // var listImg = SelectCategoryViewModel().categoryListLiveData //TODO analog
       // callback.onResult(SearchFragment.listAnimal, 1, 4, Integer(1), null)
        // callback.onResult(listAnimal, Integer(0), Integer(FIRST_PAGE+1))
        //Log.d(Constraints.TAG, "loadInitial  " + SearchFragment.listAnimal.size)
    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, Animal>
    ) {
        //  callback.onResult(listAnimal, params.key )
        Log.d(Constraints.TAG, "loadAfter ")
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, Animal>
    ) {
        //callback.onResult(listAnimal, params.key)
        Log.d(Constraints.TAG, "loadBefore ")
    }
}