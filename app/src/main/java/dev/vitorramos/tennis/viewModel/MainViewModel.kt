package dev.vitorramos.tennis.viewModel

import androidx.lifecycle.ViewModel
import dev.vitorramos.tennis.repository.MatchRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    var matchRepository: MatchRepository? = null

    fun startMatch(
        started: Long,
        gamesToSet: Int,
        setsToMatch: Int,
        hostName: String,
        guestName: String,
        onMatchCreated: (Long) -> Unit
    ) {
        GlobalScope.launch {
            val matchId = matchRepository?.insertMatch(
                started = started,
                gamesToSet = gamesToSet,
                setsToMatch = setsToMatch,
                hostName = hostName,
                guestName = guestName
            )
            if (matchId != null) {
                onMatchCreated(matchId)
            }
        }
    }
}
