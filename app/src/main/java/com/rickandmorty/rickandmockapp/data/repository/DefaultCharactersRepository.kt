package com.rickandmorty.rickandmockapp.data.repository

import com.rickandmorty.rickandmockapp.data.datasource.CharactersDataSource
import com.rickandmorty.rickandmockapp.data.map
import com.rickandmorty.rickandmockapp.data.mapper.CharactersApiModelMapper
import com.rickandmorty.rickandmockapp.domain.Response
import com.rickandmorty.rickandmockapp.domain.characters.Characters
import com.rickandmorty.rickandmockapp.domain.characters.CharactersRepository
import javax.inject.Inject

class DefaultCharactersRepository @Inject constructor(
    private val charactersDataSource: CharactersDataSource,
    private val charactersApiModelMapper: CharactersApiModelMapper
) : CharactersRepository {

    override fun getCharacters(): Response<Characters> = try {
        val response = charactersDataSource.getCharacters().execute()
        response.map(
            mapSuccess = {
                val result = charactersApiModelMapper.map(it)
                Response.Success(result)
            }
        )
    } catch (e: Exception) {
        Response.Failure(e)
    }

}