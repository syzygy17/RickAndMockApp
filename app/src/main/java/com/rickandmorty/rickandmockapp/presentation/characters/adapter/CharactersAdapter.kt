package com.rickandmorty.rickandmockapp.presentation.characters.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.rickandmorty.rickandmockapp.databinding.ItemCharacterBinding
import com.rickandmorty.rickandmockapp.domain.characters.Character

class CharactersAdapter(
    private val onItemClick: (character: Character) -> Unit
) : ListAdapter<Character, CharacterViewHolder>(CharacterDiffCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CharacterViewHolder = CharacterViewHolder(
        itemCharacterBinding = ItemCharacterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ),
        onItemClick = onItemClick
    )

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) =
        holder.bind(getItem(position))

}