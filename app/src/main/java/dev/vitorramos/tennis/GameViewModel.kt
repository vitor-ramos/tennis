package dev.vitorramos.tennis

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.vitorramos.tennis.db.dao.MatchDao
import dev.vitorramos.tennis.db.entity.MatchEntity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Deprecated(message = "Use MatchViewModel instead")
class GameViewModel : ViewModel() {
    private lateinit var dao: MatchDao
    private val matchId = 0L

    private val match: MutableLiveData<MatchEntity> by lazy {
        MutableLiveData<MatchEntity>().also {
            loadGame()
        }
    }

    private fun loadGame() {
        GlobalScope.launch {
            val gameEntity = dao.getMatch(matchId)
            if (gameEntity != null) match.value = gameEntity
        }
    }

    fun getGame(): LiveData<MatchEntity?> = match

    fun addHostPoint() {
        val currentGame = getGame().value ?: return

        when (currentGame.hostPoints) {
            // 0, 15, 30
            0, 1, 2 -> GlobalScope.launch {
                dao.updateHostPoints(currentGame.id!!, currentGame.hostPoints + 1)
            }
            // 40
            3 -> when (currentGame.guestPoints) {
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

    private fun addHostGame() {
        val currentGame = getGame().value ?: return

        GlobalScope.launch {
            dao.updateHostPoints(matchId, 0)
            dao.updateGuestPoints(matchId, 0)

            val gamesCount = currentGame.hostGames + 1
            dao.updateHostGames(matchId, gamesCount)
            if (gamesCount == currentGame.gamesToSet) addHostSet()
        }
    }

    private fun addHostSet() {
        val currentGame = getGame().value ?: return

        GlobalScope.launch {
            dao.updateHostGames(matchId, 0)
            dao.updateGuestGames(matchId, 0)

            val setsCount = currentGame.hostSets + 1
            dao.updateHostSets(matchId, setsCount)
            if (setsCount == currentGame.setsToMatch) endMatch()
        }
    }

    fun addGuestPoint() {
        val currentGame = getGame().value ?: return

        when (currentGame.guestPoints) {
            // 0, 15, 30
            0, 1, 2 -> GlobalScope.launch {
                dao.updateGuestPoints(currentGame.id!!, currentGame.guestPoints + 1)
            }
            // 40
            3 -> when (currentGame.hostPoints) {
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

    private fun addGuestGame() {
        val currentGame = getGame().value ?: return

        GlobalScope.launch {
            dao.updateHostPoints(matchId, 0)
            dao.updateGuestPoints(matchId, 0)

            val gamesCount = currentGame.guestGames + 1
            dao.updateGuestGames(matchId, gamesCount)
            if (gamesCount == currentGame.gamesToSet) addGuestSet()
        }
    }

    private fun addGuestSet() {
        val currentGame = getGame().value ?: return

        GlobalScope.launch {
            dao.updateHostGames(matchId, 0)
            dao.updateGuestGames(matchId, 0)

            val setsCount = currentGame.guestSets + 1
            dao.updateGuestSets(matchId, setsCount)
            if (setsCount == currentGame.setsToMatch) endMatch()
        }
    }

    private fun endMatch() {
    }
}
