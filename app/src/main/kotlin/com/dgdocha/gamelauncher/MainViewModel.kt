package com.dgdocha.gamelauncher

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dgdocha.gamelauncher.data.models.Game
import com.dgdocha.gamelauncher.data.repository.GameRepository
import kotlinx.coroutines.launch
import timber.log.Timber

class MainViewModel : ViewModel() {

    private val repository = GameRepository()

    private val _games = MutableLiveData<List<Game>>(emptyList())
    val games: LiveData<List<Game>> = _games

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _selectedGame = MutableLiveData<Game?>()
    val selectedGame: LiveData<Game?> = _selectedGame

    private val _categories = MutableLiveData<List<String>>(emptyList())
    val categories: LiveData<List<String>> = _categories

    init {
        loadGames()
    }

    fun loadGames() {
        viewModelScope.launch {
            _isLoading.postValue(true)
            try {
                val gamesList = repository.getInstalledGames()
                _games.postValue(gamesList)
                loadCategories(gamesList)
                Timber.d("Loaded ${gamesList.size} games")
            } catch (e: Exception) {
                Timber.e(e, "Erro ao carregar jogos")
            } finally {
                _isLoading.postValue(false)
            }
        }
    }

    private fun loadCategories(games: List<Game>) {
        val categories = games.mapNotNull { it.category }.distinct().sorted()
        _categories.postValue(categories)
    }

    fun selectGame(game: Game) {
        _selectedGame.postValue(game)
    }

    fun getGamesByCategory(category: String): List<Game> {
        return _games.value?.filter { it.category == category } ?: emptyList()
    }
}
