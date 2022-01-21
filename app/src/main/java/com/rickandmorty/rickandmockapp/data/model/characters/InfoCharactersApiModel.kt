package com.rickandmorty.rickandmockapp.data.model.characters

import com.google.gson.annotations.SerializedName

data class InfoCharactersApiModel(
    @SerializedName("count")
    val charactersAmount: Int? = null
)
