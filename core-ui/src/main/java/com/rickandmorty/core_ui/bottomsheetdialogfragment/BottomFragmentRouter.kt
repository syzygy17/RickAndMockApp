package com.rickandmorty.core_ui.bottomsheetdialogfragment

class BottomFragmentRouter {

    fun getFragment(
        title: String,
        itemList: List<Item>,
        onItemClick: (item: Item) -> Unit,
    ) = BottomFragment(
        title,
        itemList,
        onItemClick
    )

}