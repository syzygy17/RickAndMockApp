package com.rickandmorty.rickandmockapp.presentation.characters.adapter

import androidx.recyclerview.widget.DiffUtil
import com.rickandmorty.rickandmockapp.domain.characters.Character

class CharacterDiffCallback : DiffUtil.ItemCallback<Character>() {

    override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean =
        oldItem == newItem

}