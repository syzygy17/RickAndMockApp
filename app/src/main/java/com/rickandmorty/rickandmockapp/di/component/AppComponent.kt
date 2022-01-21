package com.rickandmorty.rickandmockapp.di.component

import com.rickandmorty.rickandmockapp.di.module.AppModule
import com.rickandmorty.rickandmockapp.di.module.NetworkModule
import com.rickandmorty.rickandmockapp.di.module.RepositoriesModule
import com.rickandmorty.rickandmockapp.di.module.viewmodel.ViewModelsModule
import com.rickandmorty.rickandmockapp.presentation.characters.CharactersFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        NetworkModule::class,
        RepositoriesModule::class,
        ViewModelsModule::class
    ]
)
interface AppComponent {
    fun inject(charactersFragment: CharactersFragment)
}
