package com.rickandmorty.rickandmockapp.data.model.characters

import com.google.gson.annotations.SerializedName

data class CharacterApiModel(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("species")
    val species: String? = null,
    @SerializedName("image")
    val imageUrl: String? = null
)
