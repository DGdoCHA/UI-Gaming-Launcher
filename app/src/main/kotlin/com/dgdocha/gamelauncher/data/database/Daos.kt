package com.dgdocha.gamelauncher.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.dgdocha.gamelauncher.data.models.Game
import com.dgdocha.gamelauncher.data.models.GameCategory
import com.dgdocha.gamelauncher.data.models.GameHistory
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {
    @Query("SELECT * FROM games ORDER BY name ASC")
    fun getAllGames(): Flow<List<Game>>

    @Query("SELECT * FROM games WHERE packageName = :packageName")
    suspend fun getGameByPackageName(packageName: String): Game?

    @Query("SELECT * FROM games WHERE category = :category ORDER BY name ASC")
    fun getGamesByCategory(category: String): Flow<List<Game>>

    @Query("SELECT DISTINCT category FROM games WHERE category IS NOT NULL ORDER BY category ASC")
    fun getCategories(): Flow<List<String>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGame(game: Game)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGames(games: List<Game>)

    @Update
    suspend fun updateGame(game: Game)

    @Delete
    suspend fun deleteGame(game: Game)

    @Query("DELETE FROM games WHERE packageName = :packageName")
    suspend fun deleteGameByPackageName(packageName: String)

    @Query("SELECT COUNT(*) FROM games")
    suspend fun getGameCount(): Int
}

@Dao
interface GameHistoryDao {
    @Query("SELECT * FROM game_history ORDER BY lastPlayedTime DESC")
    fun getAllHistory(): Flow<List<GameHistory>>

    @Query("SELECT * FROM game_history ORDER BY lastPlayedTime DESC LIMIT :limit")
    fun getRecentHistory(limit: Int = 10): Flow<List<GameHistory>>

    @Query("SELECT * FROM game_history WHERE packageName = :packageName")
    suspend fun getHistoryByPackageName(packageName: String): GameHistory?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHistory(history: GameHistory)

    @Update
    suspend fun updateHistory(history: GameHistory)

    @Delete
    suspend fun deleteHistory(history: GameHistory)

    @Query("DELETE FROM game_history")
    suspend fun clearHistory()
}

@Dao
interface GameCategoryDao {
    @Query("SELECT * FROM game_categories")
    fun getAllCategories(): Flow<List<GameCategory>>

    @Query("SELECT * FROM game_categories WHERE packageName = :packageName")
    suspend fun getCategoriesByPackageName(packageName: String): List<GameCategory>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(category: GameCategory)

    @Update
    suspend fun updateCategory(category: GameCategory)

    @Delete
    suspend fun deleteCategory(category: GameCategory)
}
