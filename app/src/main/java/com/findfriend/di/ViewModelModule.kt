package com.findfriend.di

import androidx.lifecycle.ViewModel
import com.findfriend.ui.animaldetailedinfo.AnimalDetailedInfoViewModel
import com.findfriend.ui.animalfavorite.AnimalsFavoriteListViewModel
import com.findfriend.ui.animalshortinfo.AnimalShortInfoViewModel
import com.findfriend.ui.mainmenu.AnimalTypeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(AnimalShortInfoViewModel::class)
    abstract fun bindShortInfoViewModule(viewModel: AnimalShortInfoViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AnimalDetailedInfoViewModel::class)
    abstract fun bindDetailedInfoViewModule(viewModel: AnimalDetailedInfoViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AnimalsFavoriteListViewModel::class)
    abstract fun bindFavoriteViewModule(viewModel: AnimalsFavoriteListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AnimalTypeViewModel::class)
    abstract fun bindTypeViewModule(viewModel: AnimalTypeViewModel): ViewModel

}