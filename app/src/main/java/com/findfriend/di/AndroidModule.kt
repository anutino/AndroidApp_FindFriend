package com.findfriend.di

import android.content.Context
import dagger.Binds
import dagger.Module
import android.app.Application

@Module
abstract class AndroidModule {
    @Binds
    abstract fun bindsContext(application : Application) : Context
}