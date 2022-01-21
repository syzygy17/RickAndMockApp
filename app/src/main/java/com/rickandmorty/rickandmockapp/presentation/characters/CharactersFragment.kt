package com.rickandmorty.rickandmockapp.presentation.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rickandmorty.core_ui.bottomsheetdialogfragment.BottomFragmentRouter
import com.rickandmorty.core_ui.bottomsheetdialogfragment.Item
import com.rickandmorty.rickandmockapp.R
import com.rickandmorty.rickandmockapp.databinding.FragmentCharactersBinding
import com.rickandmorty.rickandmockapp.di.module.viewmodel.ViewModelFactory
import com.rickandmorty.rickandmockapp.domain.characters.Character
import com.rickandmorty.rickandmockapp.domain.presentation.ViewState
import com.rickandmorty.rickandmockapp.presentation.characters.adapter.CharactersAdapter
import com.rickandmorty.rickandmockapp.utils.RickAndMockApp
import javax.inject.Inject


class CharactersFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var bottomFragmentRouter: BottomFragmentRouter

    private var _binding: FragmentCharactersBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: CharactersViewModel
    private val charactersAdapter: CharactersAdapter = CharactersAdapter {
        onCharacterClicked(it)
    }

    private fun onCharacterClicked(character: Character) {
        val action = CharactersFragmentDirections
            .actionCharactersFragmentToCharacterDetailsFragment(
                id = character.id,
                name = character.name,
                species = character.species,
                imageUrl = character.imageUrl
            )
        findNavController().navigate(action)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        RickAndMockApp.appComponent.inject(this)
        viewModel = ViewModelProvider(viewModelStore, viewModelFactory).get(
            CharactersViewModel::class.java
        )

        _binding = FragmentCharactersBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initToolbar()
        initFilterBottomFragment()
        initCharactersRecyclerView()
        startObserving()
    }

    private fun initToolbar() = with(binding.charactersToolbar) {
        titleToolbarTextView.text = getString(R.string.characters)
        mainToolbar.navigationIcon = null
    }

    private fun initFilterBottomFragment() {
        binding.filterTextView.setOnClickListener {
            bottomFragmentRouter.getFragment(
                title = getString(R.string.sort),
                itemList = getFilterList(),
                onItemClick = {
                    onFilterItemClicked(it)
                }
            ).show(childFragmentManager, null)
        }
    }

    private fun getFilterList(): List<Item> = listOf(
        Item(getString(R.string.alphabetically)),
        Item(getString(R.string.by_name_length)),
        Item(getString(R.string.reset))
    )

    private fun onFilterItemClicked(item: Item) = when (item.title) {
        getString(R.string.alphabetically) -> viewModel.onFilterListAction(FilterListAction.Alphabetically)
        getString(R.string.by_name_length) -> viewModel.onFilterListAction(FilterListAction.ByNameLength)
        getString(R.string.reset) -> viewModel.onFilterListAction(FilterListAction.Reset)
        else -> Unit
    }

    private fun initCharactersRecyclerView() = with(binding.charactersRecyclerView) {
        adapter = charactersAdapter
        layoutManager = LinearLayoutManager(requireContext())
    }

    private fun startObserving() {
        viewModel.viewState.observe(viewLifecycleOwner) {
            handleViewStateChanges(it)
        }
    }

    private fun handleViewStateChanges(state: ViewState<CharactersViewState>) = when (state) {
        is ViewState.Data -> handleCharactersViewState(state.data)
        is ViewState.Error -> onError(state.throwable)
        is ViewState.Loading -> setProgressBarIsVisible(true)
    }

    private fun handleCharactersViewState(state: CharactersViewState) = when (state) {
        is CharactersViewState.CharactersFetched -> onCharacterListFetched(state.characters.characters)
        is CharactersViewState.CharacterListSorted -> onCharacterListFetched(state.characterList)
    }

    private fun onCharacterListFetched(characterList: List<Character>) {
        setProgressBarIsVisible(false)
        charactersAdapter.submitList(characterList)
    }

    private fun onError(throwable: Throwable) {
        setProgressBarIsVisible(false)
        val message = throwable.message ?: getString(R.string.server_exception_message)
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun setProgressBarIsVisible(isVisible: Boolean) {
        binding.charactersProgressBar.isVisible = isVisible
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

}