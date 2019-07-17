package dev.vitorramos.tennis

import dev.vitorramos.tennis.db.MatchModel
import dev.vitorramos.tennis.db.entity.MatchEntity

class TheRepository(private val matchModel: MatchModel) {
    fun getMatch(matchId: Long) = matchModel.getMatch(matchId)

    suspend fun updateMatch(matchEntity: MatchEntity) = matchModel.updateMatch(matchEntity)

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
