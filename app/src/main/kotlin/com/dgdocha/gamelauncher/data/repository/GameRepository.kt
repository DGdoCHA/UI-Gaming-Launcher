package com.dgdocha.gamelauncher.data.repository

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import com.dgdocha.gamelauncher.data.models.Game
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class GameRepository(
    private val context: Context? = null,
    private val packageManager: PackageManager? = null
) {

    suspend fun getInstalledGames(): List<Game> = withContext(Dispatchers.IO) {
        if (context == null || packageManager == null) {
            return@withContext emptyList()
        }

        try {
            val packages = packageManager.getInstalledApplications(
                PackageManager.GET_META_DATA
            )

            packages
                .filter { isGame(it) }
                .mapNotNull { appInfo ->
                    try {
                        val label = appInfo.loadLabel(packageManager).toString()
                        val category = detectGameCategory(label)

                        Game(
                            packageName = appInfo.packageName,
                            name = label,
                            category = category,
                            isGame = true,
                            versionCode = appInfo.versionCode,
                            installTime = appInfo.firstInstallTime,
                            lastModified = appInfo.lastUpdateTime
                        )
                    } catch (e: Exception) {
                        Timber.e(e, "Erro ao processar app: ${appInfo.packageName}")
                        null
                    }
                }
                .sortedBy { it.name }
        } catch (e: Exception) {
            Timber.e(e, "Erro ao obter aplicações")
            emptyList()
        }
    }

    private fun isGame(appInfo: ApplicationInfo): Boolean {
        if ((appInfo.flags and ApplicationInfo.FLAG_SYSTEM) != 0) {
            return false
        }

        val packageName = appInfo.packageName.toLowerCase()
        val gameKeywords = listOf(
            "game", "play", "puzzle", "adventure", "strategy", "arcade",
            "racing", "sports", "simulation", "rpg", "action", "casual"
        )

        return gameKeywords.any { packageName.contains(it) }
    }

    private fun detectGameCategory(gameName: String): String {
        val name = gameName.toLowerCase()

        return when {
            name.contains("puzzle") || name.contains("sudoku") -> "Puzzle"
            name.contains("racing") || name.contains("car") -> "Racing"
            name.contains("rpg") || name.contains("fantasy") -> "RPG"
            name.contains("action") || name.contains("shoot") -> "Action"
            name.contains("adventure") -> "Adventure"
            name.contains("strategy") -> "Strategy"
            name.contains("casual") || name.contains("match") -> "Casual"
            name.contains("sports") -> "Sports"
            else -> "Other"
        }
    }

    suspend fun launchGame(packageName: String): Boolean = withContext(Dispatchers.IO) {
        return@withContext try {
            val intent = packageManager?.getLaunchIntentForPackage(packageName)
            if (intent != null) {
                context?.startActivity(intent)
                true
            } else {
                false
            }
        } catch (e: Exception) {
            Timber.e(e, "Erro ao abrir jogo: $packageName")
            false
        }
    }
}
