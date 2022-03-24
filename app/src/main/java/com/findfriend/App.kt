package com.findfriend

import android.app.Application
import com.findfriend.di.AppComponent
import com.findfriend.di.DaggerAppComponent

open class App : Application() {

    lateinit var mAppComponent: AppComponent
    override fun onCreate() {
        mAppComponent = DaggerAppComponent.builder().application(this).build()
        super.onCreate()
    }

    fun appComponent(): AppComponent {
        return mAppComponent
    }
}