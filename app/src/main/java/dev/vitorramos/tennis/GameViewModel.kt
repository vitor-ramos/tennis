package dev.vitorramos.tennis

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.vitorramos.tennis.db.TheDatabase
import dev.vitorramos.tennis.db.entity.GameEntity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

// TODO: INJECT
class GameViewModel(): ViewModel() {
//    private val dao = TheDatabase.db(applicationContext).gameDao()

    private val game: MutableLiveData<GameEntity> by lazy {
        MutableLiveData<GameEntity>().also {
            loadGame()
        }
    }

    private fun loadGame(applicationContext: Context, gameId: Int) {
        GlobalScope.launch {
            val gameEntity = TheDatabase.db(applicationContext).gameDao().getGame(gameId)
            if(gameEntity != null) game.value = gameEntity
        }
    }

    fun getGame(): LiveData<GameEntity?> = game

    fun addHostPoint() {
        val currentGame = getGame().value ?: return
        val dao = TheDatabase.db(applicationContext).gameDao()

        when(currentGame.hostPoints) {
            // 0, 15, 30
            0, 1, 2 -> GlobalScope.launch {
                dao.updateHostPoints(currentGame.id!!, currentGame.hostPoints + 1)
            }
            // 40
            3 -> when(currentGame.guestPoints) {
                0, 1, 2 -> {
                    addHostGame()
                }
                3 -> GlobalScope.launch {
                    dao.updateHostPoints(currentGame.id!!, currentGame.hostPoints + 1)
                }
                4 -> GlobalScope.launch {
                    dao.updateGuestPoints(currentGame.id!!, currentGame.guestPoints - 1)
                }
            }
            // A
            4 -> addHostGame()
        }
    }

    fun addHostGame(gameId: Int) {
        val currentGame = getGame().value ?: return
        val dao = TheDatabase.db(applicationContext).gameDao()

        GlobalScope.launch {
            dao.updateHostPoints(gameId, 0)
            dao.updateGuestPoints(gameId, 0)

            val gamesCount = currentGame.hostGames + 1
            dao.updateHostGames(gameId, gamesCount)
            if(gamesCount == currentGame.gamesToSet) addHostSet()
        }
    }

    fun addHostSet(gameId: Int) {
        val currentGame = getGame().value ?: return
        val dao = TheDatabase.db(applicationContext).gameDao()

        GlobalScope.launch {
            dao.updateHostGames(gameId, 0)
            dao.updateGuestGames(gameId, 0)

            val setsCount = currentGame.hostSets + 1
            dao.updateHostSets(gameId, setsCount)
            if(setsCount == currentGame.setsToMatch) endMatch()
        }
    }

    fun addGuestPoint() {
        val currentGame = getGame().value ?: return
        val dao = TheDatabase.db(applicationContext).gameDao()

        when(currentGame.guestPoints) {
            // 0, 15, 30
            0, 1, 2 -> GlobalScope.launch {
                dao.updateGuestPoints(currentGame.id!!, currentGame.guestPoints + 1)
            }
            // 40
            3 -> when(currentGame.hostPoints) {
                0, 1, 2 -> {
                    addGuestGame()
                }
                3 -> GlobalScope.launch {
                    dao.updateGuestPoints(currentGame.id!!, currentGame.guestPoints + 1)
                }
                4 -> GlobalScope.launch {
                    dao.updateHostPoints(currentGame.id!!, currentGame.hostPoints - 1)
                }
            }
            // A
            4 -> addGuestGame()
        }
    }

    fun addGuestGame(gameId: Int) {
        val currentGame = getGame().value ?: return

        GlobalScope.launch {
            dao.updateHostPoints(gameId, 0)
            dao.updateGuestPoints(gameId, 0)

            val gamesCount = currentGame.guestGames + 1
            dao.updateGuestGames(gameId, gamesCount)
            if(gamesCount == currentGame.gamesToSet) addGuestSet()
        }
    }

    fun addGuestSet(gameId: Int) {
        val currentGame = getGame().value ?: return

        GlobalScope.launch {
            dao.updateHostGames(gameId, 0)
            dao.updateGuestGames(gameId, 0)

            val setsCount = currentGame.guestSets + 1
            dao.updateGuestSets(gameId, setsCount)
            if(setsCount == currentGame.setsToMatch) endMatch()
        }
    }

    private fun endMatch() {
    }
}