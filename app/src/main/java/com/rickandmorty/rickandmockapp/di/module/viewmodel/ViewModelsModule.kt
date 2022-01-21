package com.rickandmorty.rickandmockapp.di.module.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rickandmorty.rickandmockapp.di.annotation.ViewModelKey
import com.rickandmorty.rickandmockapp.presentation.characters.CharactersViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelsModule {

    @Binds
    internal abstract fun bindsViewModelFactory(
        factory: ViewModelFactory
    ): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(CharactersViewModel::class)
    internal abstract fun bindsCharactersViewModel(
        charactersViewModel: CharactersViewModel
    ): ViewModel

}