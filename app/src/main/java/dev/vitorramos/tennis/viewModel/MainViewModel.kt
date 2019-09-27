package dev.vitorramos.tennis.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dev.vitorramos.tennis.Match
import dev.vitorramos.tennis.R
import dev.vitorramos.tennis.entity.MatchEntity
import dev.vitorramos.tennis.repository.Repository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

class MainViewModel(application: Application) : AndroidViewModel(application) {
    val matches: LiveData<Array<MatchEntity?>?> by lazy {
        Repository.it?.getMatches() ?: MutableLiveData()
    }

    private val dialogLiveData by lazy {
        MutableLiveData<(String, String, String) -> Unit>()
    }
    val dialogObservable: LiveData<(String, String, String) -> Unit> = dialogLiveData

    fun addPoint(index: Int, whichPlayer: Match.WhichPlayer) {
        matches.value?.get(index)?.let {
            val updatedMatch = Match.addPoint(whichPlayer, it)
            GlobalScope.launch {
                Repository.it?.updateMatch(updatedMatch)
            }
        }
    }

    fun deleteMatch(index: Int) {
        matches.value?.get(index)?.id?.let {
            GlobalScope.launch {
                Repository.it?.deleteMatch(it)
            }
        }
    }

    fun onClickStart() {
        dialogLiveData.postValue(onStartGame)
    }

    private val onStartGame = { hostNameInput: String, guestNameInput: String, gamesInput: String ->
        val games = if (gamesInput != "") {
            gamesInput.toInt()
        } else {
            getApplication<Application>().resources.getInteger(R.integer.default_games)
        }

        val hostName = if (hostNameInput != "") {
            hostNameInput
        } else {
            getApplication<Application>().getString(R.string.you)
        }

        val guestName = if (guestNameInput != "") {
            guestNameInput
        } else {
            getApplication<Application>().getString(R.string.guest)
        }

        startMatch(games, hostName, guestName)
    }

    private fun startMatch(gamesToSet: Int, hostName: String, guestName: String) {
        GlobalScope.launch {
            Repository.it?.insertMatch(
                started = Date().time,
                gamesToSet = gamesToSet,
                hostName = hostName,
                guestName = guestName
            )
        }
    }
}
