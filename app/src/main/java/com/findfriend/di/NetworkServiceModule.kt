package com.findfriend.di

import com.findfriend.networkservice.NetworkService
import dagger.Module
import dagger.Provides

@Module
class NetworkServiceModule {

    @Provides
    fun provideNetworkService(): NetworkService{
        return NetworkService()
    }
}