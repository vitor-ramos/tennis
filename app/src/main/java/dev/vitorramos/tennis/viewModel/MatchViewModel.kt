package dev.vitorramos.tennis.viewModel

import android.app.Application
import android.content.DialogInterface
import android.content.DialogInterface.BUTTON_POSITIVE
import androidx.annotation.IdRes
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dev.vitorramos.tennis.Match
import dev.vitorramos.tennis.Match.WhichPlayer
import dev.vitorramos.tennis.R
import dev.vitorramos.tennis.entity.MatchEntity
import dev.vitorramos.tennis.repository.Repository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

class MatchViewModel(application: Application) : AndroidViewModel(application) {
    private var matchId: Long? = null
        set(value) {
            field = value
            value?.let {
                Repository.it?.getMatch(it)?.let { liveData ->
                    currentMatch = liveData
                }
            }
        }

    var currentMatch: LiveData<MatchEntity?> = MutableLiveData()

    private val deleteLiveData = MutableLiveData<Boolean>()
    fun deleteObservable(): LiveData<Boolean> = deleteLiveData

    private val finishLiveData = MutableLiveData<Boolean>()
    fun finishObservable(): LiveData<Boolean> = finishLiveData

    private val startMatchLiveData = MutableLiveData<Boolean>()
    fun startMatchObservable(): LiveData<Boolean> = startMatchLiveData

    private fun addPoint(whichPlayer: WhichPlayer) {
        currentMatch.value?.let {
            val updatedMatch = Match.addPoint(whichPlayer, it)
            GlobalScope.launch {
                Repository.it?.updateMatch(updatedMatch)
            }
        }
    }

    private fun deleteMatch() {
        matchId?.let {
            GlobalScope.launch {
                Repository.it?.deleteMatch(it)
            }
        }
    }

    private fun startMatch(gamesToSet: Int, hostName: String, guestName: String) {
        GlobalScope.launch {
            matchId = Repository.it?.insertMatch(
                started = Date().time,
                gamesToSet = gamesToSet,
                hostName = hostName,
                guestName = guestName
            )
        }
    }

    fun onOptionItemSelected(@IdRes id: Int): Boolean {
        return if (id == R.id.menu_match_delete && currentMatch.value != null) {
            deleteLiveData.postValue(true)
            true
        } else false
    }

    fun onClick(@IdRes id: Int) {
        when (id) {
            R.id.cl_match_host -> addPoint(WhichPlayer.HOST)
            R.id.cl_match_guest -> addPoint(WhichPlayer.GUEST)
        }
    }

    val onDelete = { dialog: DialogInterface, which: Int ->
        if (which == BUTTON_POSITIVE) {
            finishLiveData.postValue(true)
            deleteMatch()
        }
        dialog.dismiss()
    }

    val onStart = { dialog: DialogInterface,
                    hostNameInput: String,
                    guestNameInput: String,
                    gamesInput: String ->
        dialog.dismiss()

        val games = if (gamesInput != "") {
            gamesInput.toInt()
        } else {
            application.resources.getInteger(R.integer.default_games)
        }

        val hostName = if (hostNameInput != "") {
            hostNameInput
        } else {
            application.getString(R.string.you)
        }

        val guestName = if (guestNameInput != "") {
            guestNameInput
        } else {
            application.getString(R.string.guest)
        }

        startMatch(games, hostName, guestName)
    }

    fun onMatchIdLoaded(matchId: Long?) {
        if (matchId != null) {
            this.matchId = matchId
        } else {
            startMatchLiveData.postValue(true)
        }
    }
}
