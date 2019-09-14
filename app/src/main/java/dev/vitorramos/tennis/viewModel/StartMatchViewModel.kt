package dev.vitorramos.tennis.viewModel

import androidx.lifecycle.ViewModel
import dev.vitorramos.tennis.repository.Repository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

class StartMatchViewModel : ViewModel() {
    fun startMatch(
        gamesToSet: Int,
        hostName: String,
        guestName: String,
        onMatchCreated: (Long) -> Unit
    ) {
        GlobalScope.launch {
            val matchId = Repository.it?.insertMatch(
                started = Date().time,
                gamesToSet = gamesToSet,
                hostName = hostName,
                guestName = guestName
            )
            if (matchId != null) {
                onMatchCreated(matchId)
            }
        }
    }
}
