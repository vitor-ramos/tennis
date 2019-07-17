package dev.vitorramos.tennis.db

import androidx.lifecycle.LiveData
import dev.vitorramos.tennis.Score
import dev.vitorramos.tennis.db.entity.MatchEntity

class MatchModel(private val theDatabase: TheDatabase) {
    fun getMatch(matchId: Long): LiveData<MatchEntity?>? {
        return theDatabase.matchDao().getMatch(matchId)
    }

    suspend fun updateHostScore(matchId: Long, score: Score) {
        return theDatabase.matchDao().updateHostScore(matchId, score)
    }

    suspend fun updateGuestScore(matchId: Long, score: Score) {
        return theDatabase.matchDao().updateGuestScore(matchId, score)
    }

    suspend fun insertMatch(
        started: Long,
        ended: Long? = null,
        gamesToSet: Int,
        setsToMatch: Int,
        hostName: String = "",
        guestName: String = ""
    ): Long? {
        val match = MatchEntity(
            started = started,
            ended = ended,
            gamesToSet = gamesToSet,
            setsToMatch = setsToMatch,
            hostName = hostName,
            guestName = guestName
        )
        return theDatabase.matchDao().insertMatch(match)
    }
}
