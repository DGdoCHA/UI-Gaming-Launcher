package com.dgdocha.gamelauncher.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dgdocha.gamelauncher.data.models.Game
import com.dgdocha.gamelauncher.data.models.GameCategory
import com.dgdocha.gamelauncher.data.models.GameHistory
import com.dgdocha.gamelauncher.util.DateConverter

@Database(
    entities = [Game::class, GameHistory::class, GameCategory::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(DateConverter::class)
abstract class GameDatabase : RoomDatabase() {

    abstract fun gameDao(): GameDao
    abstract fun gameHistoryDao(): GameHistoryDao
    abstract fun gameCategoryDao(): GameCategoryDao

    companion object {
        @Volatile
        private var instance: GameDatabase? = null

        fun getInstance(context: Context): GameDatabase {
            return instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    GameDatabase::class.java,
                    "game_launcher_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { instance = it }
            }
        }
    }
}
