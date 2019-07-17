package dev.vitorramos.tennis.matchScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.vitorramos.tennis.TheRepository
import dev.vitorramos.tennis.db.entity.MatchEntity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MatchViewModel : ViewModel() {
    var theRepository: TheRepository? = null

    fun getMatch(matchId: Long): LiveData<MatchEntity?> {
        val mutableLiveData = MutableLiveData<MatchEntity?>()
        GlobalScope.launch {
            val match = theRepository?.getMatch(matchId)
            mutableLiveData.postValue(match)
        }
        return mutableLiveData
    }

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
            if(matchId != null) onMatchCreated(matchId)
        }
    }

    fun addHostPoint() {
    }

    fun addGuestPoint() {
    }
}
