package com.findfriend

import android.app.Application
import com.findfriend.di.AppComponent

class FindFriendApplication : Application() {

    lateinit var mAppComponent : AppComponent
    override fun onCreate() {
        super.onCreate()
        mAppComponent = DaggerAppComponent.builder().appModule()
    }
}