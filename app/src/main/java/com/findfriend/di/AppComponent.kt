package com.findfriend.di

import android.app.Application
import com.findfriend.ui.animaldetailedinfo.AnimalDetailedInfoFragment
import com.findfriend.ui.animalfavorite.AnimalFavoriteFragment
import com.findfriend.ui.animalshortinfo.AnimalShortInfoListFragment
import com.findfriend.ui.mainmenu.AnimalTypeSelectorFragment
import com.findfriend.ui.profile.ProfileFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RepositoryModule::class, ViewModelModule::class,
    ViewModuleFactoryModule::class, AndroidModule::class])
interface AppComponent {
    fun inject(fragment: AnimalDetailedInfoFragment)
    fun inject(fragment: AnimalShortInfoListFragment)
    fun inject(fragment: AnimalFavoriteFragment)
    fun inject(fragment: ProfileFragment)
    fun inject(animalTypeSelectorFragment: AnimalTypeSelectorFragment)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent;
    }
}