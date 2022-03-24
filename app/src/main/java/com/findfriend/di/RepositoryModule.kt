package com.findfriend.di

import com.findfriend.data.repository.AnimalRepositoryImpl
import com.findfriend.data.repository.AnimalRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindAnimalRepositoryImpl(repository: AnimalRepositoryImpl): AnimalRepository
}