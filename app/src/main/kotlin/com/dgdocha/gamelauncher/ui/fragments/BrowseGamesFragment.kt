package com.dgdocha.gamelauncher.ui.fragments

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dgdocha.gamelauncher.MainViewModel
import com.dgdocha.gamelauncher.R
import com.dgdocha.gamelauncher.ui.adapters.GamesGridAdapter
import timber.log.Timber

class BrowseGamesFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: GamesGridAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_browse_games, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        setupRecyclerView(view)
        observeGames()
    }

    private fun setupRecyclerView(view: View) {
        recyclerView = view.findViewById(R.id.games_recycler_view)
        adapter = GamesGridAdapter { game ->
            viewModel.selectGame(game)
            Timber.d("Game selected: ${game.name}")
        }

        recyclerView.layoutManager = GridLayoutManager(context, 4)
        recyclerView.adapter = adapter
        recyclerView.isFocusable = true
        recyclerView.requestFocus()
    }

    private fun observeGames() {
        viewModel.games.observe(viewLifecycleOwner) { games ->
            Timber.d("Games updated: ${games.size} games")
            adapter.submitList(games)
        }
    }
}
