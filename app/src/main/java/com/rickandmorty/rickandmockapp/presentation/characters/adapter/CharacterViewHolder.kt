package com.rickandmorty.rickandmockapp.presentation.characters.adapter

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.rickandmorty.rickandmockapp.databinding.ItemCharacterBinding
import com.rickandmorty.rickandmockapp.domain.characters.Character

class CharacterViewHolder(
    private val itemCharacterBinding: ItemCharacterBinding,
    private val onItemClick: (character: Character) -> Unit
) : RecyclerView.ViewHolder(itemCharacterBinding.root) {

    fun bind(character: Character) = with(itemCharacterBinding) {
        itemTitle.text = character.name
        itemDescription.text = character.species
        itemImage.load(character.imageUrl)
        itemView.setOnClickListener {
            onItemClick(character)
        }
    }

}