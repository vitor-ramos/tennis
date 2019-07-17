package dev.vitorramos.tennis.db

import androidx.lifecycle.LiveData
import dev.vitorramos.tennis.db.entity.MatchEntity

class MatchModel(private val theDatabase: TheDatabase) {
    fun getMatch(matchId: Long): LiveData<MatchEntity?>? {
        return theDatabase.matchDao().getMatch(matchId)
    }

    suspend fun updateMatch(matchEntity: MatchEntity) {
        return theDatabase.matchDao().updateMatch(matchEntity)
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
