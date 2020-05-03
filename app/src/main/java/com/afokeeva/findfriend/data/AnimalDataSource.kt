package com.afokeeva.findfriend.data

import android.util.Log
import androidx.constraintlayout.widget.Constraints
import androidx.paging.PageKeyedDataSource
import com.afokeeva.findfriend.ui.fragment.SearchFragment

class AnimalDataSource(var useSearchFragment: Boolean) : PageKeyedDataSource<Integer, Animal>() {
    val PAGE_SIZE = 50;
    val FIRST_PAGE = 9;
    var initialParams: LoadInitialParams<Integer>? = null

    override fun loadInitial(
        params: LoadInitialParams<Integer>,
        callback: LoadInitialCallback<Integer, Animal>
    ) {
        val i = 1
        if (useSearchFragment) {
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
        params: LoadParams<Integer>,
        callback: LoadCallback<Integer, Animal>
    ) {
        //  callback.onResult(listAnimal, params.key )
        Log.d(Constraints.TAG, "loadAfter ")
    }

    override fun loadBefore(
        params: LoadParams<Integer>,
        callback: LoadCallback<Integer, Animal>
    ) {
        //callback.onResult(listAnimal, params.key)
        Log.d(Constraints.TAG, "loadBefore ")
    }
}