package com.rickandmorty.rickandmockapp.data.mapper

import com.rickandmorty.rickandmockapp.data.model.characters.CharacterApiModel
import com.rickandmorty.rickandmockapp.data.model.characters.CharactersApiModel
import com.rickandmorty.rickandmockapp.domain.characters.Character
import com.rickandmorty.rickandmockapp.domain.characters.Characters
import com.rickandmorty.rickandmockapp.domain.characters.InfoCharacters
import com.rickandmorty.rickandmockapp.domain.mapper.Mapper
import com.rickandmorty.rickandmockapp.utils.EMPTY_STRING
import com.rickandmorty.rickandmockapp.utils.NO_ID
import com.rickandmorty.rickandmockapp.utils.ZERO_CHARACTERS
import javax.inject.Inject

class CharactersApiModelMapper @Inject constructor() : Mapper<CharactersApiModel, Characters>() {

    override fun map(from: CharactersApiModel): Characters = Characters(
        infoCharacters = InfoCharacters(
            charactersAmount = from.info?.charactersAmount ?: ZERO_CHARACTERS
        ),
        characters = getCharacterList(from.characters ?: emptyList())
    )

    private fun getCharacterList(characters: List<CharacterApiModel>): List<Character> =
        characters.map {
            Character(
                id = it.id ?: NO_ID,
                name = it.name ?: EMPTY_STRING,
                species = it.species ?: EMPTY_STRING,
                imageUrl = it.imageUrl ?: EMPTY_STRING
            )
        }

}
