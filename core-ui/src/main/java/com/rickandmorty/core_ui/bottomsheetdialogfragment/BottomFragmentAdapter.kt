package com.rickandmorty.core_ui.bottomsheetdialogfragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rickandmorty.core_ui.databinding.ItemBottomFragmentBinding

class BottomFragmentAdapter(
    private val itemList: List<Item>,
    private val onItemClick: (item: Item) -> Unit,
) : RecyclerView.Adapter<BottomFragmentViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BottomFragmentViewHolder = BottomFragmentViewHolder(
        itemBottomFragmentBinding = ItemBottomFragmentBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ),
        onItemClick = onItemClick
    )

    override fun onBindViewHolder(holder: BottomFragmentViewHolder, position: Int) =
        holder.bind(itemList[position])

    override fun getItemCount(): Int = itemList.size

}
