package dev.vitorramos.tennis.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.vitorramos.tennis.Match
import dev.vitorramos.tennis.Match.WhichPlayer
import dev.vitorramos.tennis.repository.TennisRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MatchViewModel : ViewModel() {
    var tennisRepository: TennisRepository? = null
    var matchId: Long? = null

    val currentMatch by lazy { tennisRepository?.getMatch(matchId!!) ?: MutableLiveData() }

    fun addHostPoint() = addPoint(WhichPlayer.HOST)

    fun addGuestPoint() = addPoint(WhichPlayer.GUEST)

    private fun addPoint(whichPlayer: WhichPlayer) {
        if (currentMatch.value != null) {
            val updatedMatch = Match.addPoint(whichPlayer, currentMatch.value!!)
            GlobalScope.launch {
                tennisRepository?.updateMatch(updatedMatch)
            }
        }
    }
}
