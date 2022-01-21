package com.rickandmorty.rickandmockapp.di.module

import com.rickandmorty.core_ui.bottomsheetdialogfragment.BottomFragmentRouter
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
class AppModule {

    @Provides
    @Reusable
    fun provideBottomFragmentRouter(): BottomFragmentRouter = BottomFragmentRouter()

}