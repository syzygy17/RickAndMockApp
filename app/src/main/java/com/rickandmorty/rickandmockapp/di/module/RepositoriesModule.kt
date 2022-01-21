package com.rickandmorty.rickandmockapp.di.module

import com.rickandmorty.rickandmockapp.data.repository.DefaultCharactersRepository
import com.rickandmorty.rickandmockapp.domain.characters.CharactersRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoriesModule {

    @Binds
    abstract fun bindsCharactersRepository(
        defaultCharactersRepository: DefaultCharactersRepository
    ): CharactersRepository

}