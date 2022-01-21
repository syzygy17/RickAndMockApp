package com.rickandmorty.core_ui.bottomsheetdialogfragment

import androidx.recyclerview.widget.RecyclerView
import com.rickandmorty.core_ui.databinding.ItemBottomFragmentBinding

class BottomFragmentViewHolder(
    private val itemBottomFragmentBinding: ItemBottomFragmentBinding,
    private val onItemClick: (item: Item) -> Unit
) : RecyclerView.ViewHolder(itemBottomFragmentBinding.root) {

    fun bind(item: Item) = with(itemBottomFragmentBinding) {
        itemTitle.text = item.title
        itemView.setOnClickListener {
            onItemClick(item)
        }
    }

}