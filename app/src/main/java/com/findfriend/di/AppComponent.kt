package com.findfriend.di

import com.findfriend.MainActivity
import com.findfriend.data.AnimalDetailedInfo
import com.findfriend.data.AnimalFavoriteList
import com.findfriend.ui.animalshortinfo.AnimalShortInfoListFragment
import com.findfriend.ui.mainmenu.MainFragment
import com.findfriend.ui.profile.ProfileFragment
import dagger.Component
import javax.inject.Singleton

@Component(modules = [RepositoryModule::class, NetworkServiceModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(fragment: MainFragment)
    fun inject(fragment: AnimalDetailedInfo)
    fun inject(fragment: AnimalShortInfoListFragment)
    fun inject(fragment: AnimalFavoriteList)
    fun inject(fragment: ProfileFragment)
}