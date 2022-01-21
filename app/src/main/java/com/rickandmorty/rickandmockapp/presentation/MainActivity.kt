package com.rickandmorty.rickandmockapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rickandmorty.rickandmockapp.R
import com.rickandmorty.rickandmockapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_RickAndMockApp)
        super.onCreate(savedInstanceState)
        setContentView(ActivityMainBinding.inflate(layoutInflater).root)
    }
}