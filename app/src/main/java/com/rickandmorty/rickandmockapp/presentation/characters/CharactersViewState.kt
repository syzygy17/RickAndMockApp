package com.rickandmorty.rickandmockapp.presentation.characters

import com.rickandmorty.rickandmockapp.domain.characters.Character
import com.rickandmorty.rickandmockapp.domain.characters.Characters

sealed class CharactersViewState {
    data class CharactersFetched(val characters: Characters) : CharactersViewState()
    data class CharacterListSorted(val characterList: List<Character>) : CharactersViewState()
}
