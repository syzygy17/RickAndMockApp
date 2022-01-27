package com.rickandmorty.rickandmockapp.di.module

import com.rickandmorty.rickandmockapp.data.repository.DefaultCharactersRepository
import com.rickandmorty.rickandmockapp.domain.characters.CharactersRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepositoriesModule {

    @Singleton
    @Binds
    abstract fun bindsCharactersRepository(
        defaultCharactersRepository: DefaultCharactersRepository
    ): CharactersRepository

}