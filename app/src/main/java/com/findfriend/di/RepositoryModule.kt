package com.findfriend.di

import com.findfriend.data.AnimalRepositoryImpl
import com.findfriend.networkservice.NetworkService
import com.findfriend.repository.AnimalRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideAnimalRepository(networkService: NetworkService): AnimalRepository {
        return AnimalRepositoryImpl(networkService)
    }
    //   @Binds
    //  abstract fun bindAnimalUserRepository(repository: AnimalRepositoryImpl): AnimalRepository
}