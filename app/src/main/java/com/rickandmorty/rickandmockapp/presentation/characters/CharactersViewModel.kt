package com.rickandmorty.rickandmockapp.presentation.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rickandmorty.rickandmockapp.domain.Response
import com.rickandmorty.rickandmockapp.domain.characters.Character
import com.rickandmorty.rickandmockapp.domain.characters.Characters
import com.rickandmorty.rickandmockapp.domain.characters.CharactersRepository
import com.rickandmorty.rickandmockapp.domain.presentation.ViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.properties.Delegates

class CharactersViewModel @Inject constructor(
    private val charactersRepository: CharactersRepository
) : ViewModel() {

    private val _viewState = MutableLiveData<ViewState<CharactersViewState>>()

    val viewState: LiveData<ViewState<CharactersViewState>> = _viewState

    private var characterList: List<Character> by Delegates.notNull()

    fun onFilterListAction(state: FilterListAction) {
        _viewState.value = ViewState.Loading
        _viewState.postValue(
            ViewState.Data(
                CharactersViewState.CharacterListSorted(
                    sortCharacterListByState(state)
                )
            )
        )
    }

    private fun sortCharacterListByState(state: FilterListAction): List<Character> = when (state) {
        is FilterListAction.Alphabetically -> characterList.sortedBy(Character::name)
        is FilterListAction.ByNameLength -> characterList.sortedBy { it.name.length }
        is FilterListAction.Reset -> characterList.sortedBy(Character::id)
    }

    init {
        _viewState.value = ViewState.Loading
        viewModelScope.launch {
            getCharacters()
        }
    }

    private suspend fun getCharacters() = withContext(Dispatchers.IO) {
        when (val result = charactersRepository.getCharacters()) {
            is Response.Success -> onCharactersFetched(result.data)
            is Response.Failure -> onError(result.data)
        }
    }

    private fun onCharactersFetched(characters: Characters) {
        characterList = characters.characters
        _viewState.postValue(ViewState.Data(CharactersViewState.CharactersFetched(characters)))
    }

    private fun onError(throwable: Throwable) = _viewState.postValue(ViewState.Error(throwable))

}