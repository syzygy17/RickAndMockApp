package com.rickandmorty.rickandmockapp.data.model.characters

import com.google.gson.annotations.SerializedName

data class CharactersApiModel(
    @SerializedName("info")
    val info: InfoCharactersApiModel? = null,
    @SerializedName("results")
    val characters: List<CharacterApiModel>? = null
)
