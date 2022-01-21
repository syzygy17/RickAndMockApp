package com.rickandmorty.rickandmockapp.data.datasource

import com.rickandmorty.rickandmockapp.data.model.characters.CharactersApiModel
import retrofit2.Call
import retrofit2.http.GET

interface CharactersDataSource {

    @GET("character")
    fun getCharacters(): Call<CharactersApiModel>

}