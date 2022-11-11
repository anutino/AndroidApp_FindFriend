package com.findfriend.ui.di

import android.content.Context
import dagger.Binds
import dagger.Module
import android.app.Application
import com.findfriend.data.AnimalApiImpl
import com.findfriend.data.networkservice.NetworkService
import com.findfriend.data.testdatastorage.TestAnimalData
import com.findfriend.domain.AnimalApi
import dagger.Provides

@Module
abstract class AndroidModule {
    @Binds
    abstract fun bindsContext(application: Application): Context

    companion object {
        @Provides
        fun bindAnimalApiImpl(networkService: NetworkService,
                              testAnimalData: TestAnimalData
        ): AnimalApi {
            return AnimalApiImpl(networkService, testAnimalData)
        }
    }

}