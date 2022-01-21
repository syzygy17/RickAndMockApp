package com.rickandmorty.core_ui.bottomsheetdialogfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.rickandmorty.core_ui.R
import com.rickandmorty.core_ui.databinding.FragmentBottomBinding

class BottomFragment(
    private val title: String,
    private val itemList: List<Item>,
    private val onItemClick: (item: Item) -> Unit,
) : BottomSheetDialogFragment() {

    private var _binding: FragmentBottomBinding? = null
    private val binding get() = _binding!!

    override fun getTheme(): Int = R.style.BottomSheetDialogTheme

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBottomBinding.inflate(inflater, container, false)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViews() = with(binding) {
        titleTextView.text = title
        bottomFragmentRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        bottomFragmentRecyclerView.adapter = BottomFragmentAdapter(itemList) {
            onItemClick(it)
            dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

}