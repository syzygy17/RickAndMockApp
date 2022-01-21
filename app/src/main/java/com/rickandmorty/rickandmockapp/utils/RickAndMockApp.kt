package com.rickandmorty.rickandmockapp.utils

import android.app.Application
import com.rickandmorty.rickandmockapp.di.component.AppComponent
import com.rickandmorty.rickandmockapp.di.component.DaggerAppComponent

class RickAndMockApp : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent
            .create()
    }

}
