package com.rickandmorty.rickandmockapp.presentation.characterdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.rickandmorty.rickandmockapp.databinding.FragmentCharacterDetailsBinding


class CharacterDetailsFragment : Fragment() {

    private var _binding: FragmentCharacterDetailsBinding? = null
    private val binding get() = _binding!!
    private val args: CharacterDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharacterDetailsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initToolbar()
        initCharacterView()
    }

    private fun initToolbar() = with(binding.characterDetailsToolbar) {
        titleToolbarTextView.text = args.name
        mainToolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun initCharacterView() = with(binding) {
        characterImage.load(args.imageUrl)
        characterName.text = args.name
        characterSpecies.text = args.species
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

}