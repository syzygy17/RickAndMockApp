package com.rickandmorty.rickandmockapp.presentation.characters

sealed class FilterListAction {
    object Alphabetically : FilterListAction()
    object ByNameLength : FilterListAction()
    object Reset : FilterListAction()
}
