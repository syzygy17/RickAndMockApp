package com.rickandmorty.rickandmockapp.domain.characters

import com.rickandmorty.rickandmockapp.domain.Response

interface CharactersRepository {

    fun getCharacters(): Response<Characters>

}