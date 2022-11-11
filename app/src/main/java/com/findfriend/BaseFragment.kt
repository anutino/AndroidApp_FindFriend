package com.findfriend;

import android.content.Context
import androidx.lifecycle.ViewModel
import com.findfriend.ui.di.AppComponent

abstract class BaseFragment<VM : ViewModel> : ViewModelFragment<VM>() {

    override fun onAttach(context: Context) {
        injectDependencies(appComponent())
        super.onAttach(context)
    }

    private fun appComponent(): AppComponent {
        return (activity?.application as App).appComponent()
    }

    protected abstract fun injectDependencies(appComponent: AppComponent)

}
