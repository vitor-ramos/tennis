package dev.vitorramos.tennis.matchScreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.vitorramos.tennis.Match
import dev.vitorramos.tennis.TheRepository
import dev.vitorramos.tennis.WhichPlayer
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MatchViewModel : ViewModel() {
    var theRepository: TheRepository? = null
    var matchId: Long? = null
    private var match: Match? = null

    val currentMatch by lazy { theRepository?.getMatch(matchId!!) ?: MutableLiveData() }

    fun startMatch(
        started: Long,
        gamesToSet: Int,
        setsToMatch: Int,
        hostName: String,
        guestName: String,
        onMatchCreated: (Long) -> Unit
    ) {
        GlobalScope.launch {
            val matchId = theRepository?.insertMatch(
                started = started,
                gamesToSet = gamesToSet,
                setsToMatch = setsToMatch,
                hostName = hostName,
                guestName = guestName
            )
            if (matchId != null) {
                match = Match(matchId, gamesToSet, setsToMatch)
                onMatchCreated(matchId)
            }
        }
    }

    fun addHostPoint() {
        GlobalScope.launch {
            match?.let {
                it.addPoint(WhichPlayer.HOST)
                theRepository?.updateHostScore(it.id, it.hostScore)
            }
        }
    }

    fun addGuestPoint() {
        GlobalScope.launch {
            match?.let{
                it.addPoint(WhichPlayer.GUEST)
                theRepository?.updateGuestScore(it.id, it.guestScore)
            }
        }
    }
}
