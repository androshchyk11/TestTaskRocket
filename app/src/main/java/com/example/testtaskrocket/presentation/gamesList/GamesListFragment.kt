package com.example.testtaskrocket.presentation.gamesList


import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testtaskrocket.R
import com.example.testtaskrocket.databinding.FragmentGamesListBinding
import com.example.testtaskrocket.presentation.baseFragment.BaseFragment
import com.example.testtaskrocket.presentation.extensions.textChanges
import com.example.testtaskrocket.presentation.gameDetails.GameDetailsFragment
import com.example.testtaskrocket.presentation.viewState.ViewState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class GamesListFragment : BaseFragment<FragmentGamesListBinding>(R.layout.fragment_games_list) {

    private val gamesViewModel: GamesListViewModel by viewModels()

    @Inject
    lateinit var gamesAdapter: GamesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        setUpSearch()
    }

    override fun setUpViewModelCallbacks() {
        gamesViewModel.accessoriesLiveData.observe(viewLifecycleOwner) { result ->
            when (result) {
                is ViewState.Loading -> binding.progressBar.visibility = View.VISIBLE
                is ViewState.Success -> {
                    binding.progressBar.visibility = View.GONE
                    val previousSize = gamesAdapter.items.size
                    gamesAdapter.items.addAll(result.result)
                    gamesAdapter.notifyItemRangeChanged(previousSize, 10)
                    gamesViewModel.changeToDefaultValue()
                }
                is ViewState.Error -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), result.errorInfo.message, Toast.LENGTH_SHORT).show()
                    gamesViewModel.changeToDefaultValue()
                }
                is ViewState.Default -> {}
            }
        }

        gamesViewModel.searchLiveData.observe(viewLifecycleOwner) {}
    }

    override fun setUpAdapters() {
        gamesAdapter.onClickListener = { position ->
            val bundle = bundleOf(
                (GameDetailsFragment.GAME_TITLE to gamesAdapter.items[position].name),
                (GameDetailsFragment.GAME_DESCRIPTION to gamesAdapter.items[position].description),
                (GameDetailsFragment.GAME_IMAGE_URL to gamesAdapter.items[position].image.iconUrl)
            )

            findNavController().navigate(R.id.action_gamesListFragment_to_gameDetailsFragment, bundle)
        }
    }

    private fun setUpRecyclerView() {
        binding.gamesRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = gamesAdapter
        }
    }

    @OptIn(FlowPreview::class)
    private fun setUpSearch() {
        binding.search.textChanges()
            .debounce(300)
            .onEach {
                if (gamesViewModel.searchLiveData.value != it.toString()) { // to prevent reloading page after getting back
                    gamesViewModel.searchLiveData.value = it.toString()
                    val previousSize = gamesAdapter.items.size
                    val previousLastIndex = gamesAdapter.items.lastIndex
                    gamesAdapter.items.clear()
                    gamesAdapter.notifyItemRangeRemoved(previousLastIndex, previousSize)
                    gamesViewModel.getAccessories("name:${it.toString()}")
                }
            }.launchIn(lifecycleScope)
    }
}