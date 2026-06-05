package com.dgdocha.gamelauncher.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "games")
data class Game(
    @PrimaryKey
    val packageName: String,
    val name: String,
    val category: String? = null,
    val thumbnail: String? = null,
    val cover: String? = null,
    val isGame: Boolean = true,
    val versionCode: Int = 0,
    val installTime: Long = 0,
    val lastModified: Long = 0
)

@Entity(tableName = "game_history")
data class GameHistory(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val packageName: String,
    val gameName: String,
    val lastPlayedTime: Date = Date(),
    val playCount: Int = 1,
    val totalPlayTime: Long = 0
)

@Entity(tableName = "game_categories")
data class GameCategory(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val packageName: String,
    val category: String,
    val customName: String? = null
)
