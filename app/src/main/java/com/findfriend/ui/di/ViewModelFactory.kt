package com.findfriend.ui.di

import androidx.annotation.NonNull
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException
import javax.inject.Provider
import javax.inject.Singleton
import javax.inject.Inject

@Singleton
class ViewModelFactory @Inject constructor(@NonNull map: Map<Class<out ViewModel>,
        @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {

    @NonNull
    var mVmProviderMap: Map<Class<out ViewModel>, Provider<ViewModel>> = map

    @NonNull
    override fun <T : ViewModel> create(@NonNull modelClass: Class<T>): T {
        val provider = mVmProviderMap[modelClass]
        return if (provider != null) {
            provider.get() as T
        } else {
            throw IllegalArgumentException(
                "Provider for ViewModel class = [" + modelClass.name + "] not found")
        }
    }
}