package com.findfriend

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

abstract class ViewModelFragment<VM : ViewModel> : Fragment() {
    @Inject
    protected lateinit var viewModelFactory: ViewModelProvider.Factory

    protected lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (viewModelFactory == null) {
            viewModelFactory =
                ViewModelProvider.AndroidViewModelFactory.getInstance(activity?.application!!)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = provideViewModel(getViewModelClass(), viewModelFactory)
    }

    private fun <T : ViewModel> provideViewModel(modelClass: Class<T>,
                                                 factory: ViewModelProvider.Factory
    ): T {
        return getViewModelProvider(factory)[modelClass]
    }

    private fun getViewModelProvider(factory: ViewModelProvider.Factory): ViewModelProvider {
        return ViewModelProvider(viewModelStore, factory)
    }

    protected abstract fun getViewModelClass(): Class<VM>

}