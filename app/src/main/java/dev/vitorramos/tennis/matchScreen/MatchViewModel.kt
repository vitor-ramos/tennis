package dev.vitorramos.tennis.matchScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.vitorramos.tennis.TheRepository
import dev.vitorramos.tennis.db.entity.MatchEntity
import javax.inject.Inject

class MatchViewModel : ViewModel() {
    fun getMatch(): LiveData<MatchEntity?> {
        return MutableLiveData()
    }

    fun startMatch(
        started: Long,
        gamesToSet: Int,
        setsToMatch: Int,
        hostName: String,
        guestName: String
    ) {
        TheRepository.insertMatch(
            started = started,
            gamesToSet = gamesToSet,
            setsToMatch = setsToMatch,
            hostName = hostName,
            guestName = guestName
        )
    }

    fun addHostPoint() {
    }

    fun addGuestPoint() {
    }
}
