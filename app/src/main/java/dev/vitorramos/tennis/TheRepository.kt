package dev.vitorramos.tennis

import androidx.lifecycle.LiveData
import dev.vitorramos.tennis.db.MatchModel
import dev.vitorramos.tennis.db.entity.MatchEntity

class TheRepository(private val matchModel: MatchModel) {
    fun getMatch(matchId: Long): LiveData<MatchEntity?>? {
        return matchModel.getMatch(matchId)
    }

    suspend fun updateHostScore(matchId: Long, score: Score) {
        return matchModel.updateHostScore(matchId, score)
    }

    suspend fun updateGuestScore(matchId: Long, score: Score) {
        return matchModel.updateGuestScore(matchId, score)
    }

    suspend fun insertMatch(
        started: Long,
        ended: Long? = null,
        gamesToSet: Int,
        setsToMatch: Int,
        hostName: String = "",
        guestName: String = ""
    ): Long? {
        return matchModel.insertMatch(
            started = started,
            ended = ended,
            gamesToSet = gamesToSet,
            setsToMatch = setsToMatch,
            hostName = hostName,
            guestName = guestName
        )
    }
}
