package dev.vitorramos.tennis.viewModel

import androidx.lifecycle.ViewModel
import dev.vitorramos.tennis.repository.Repository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class StartMatchViewModel : ViewModel() {
    fun startMatch(
        gamesToSet: Int,
        setsToMatch: Int,
        hostName: String,
        guestName: String,
        onMatchCreated: (Long) -> Unit
    ) {
        GlobalScope.launch {
            val matchId = Repository.it?.insertMatch(
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
