package dev.vitorramos.tennis.model

import dev.vitorramos.tennis.TennisDatabase
import dev.vitorramos.tennis.entity.MatchEntity

class MatchModel(private val tennisDatabase: TennisDatabase) {
    fun getMatch(matchId: Long) = tennisDatabase.matchDao().getMatch(matchId)


    fun getMatches() = tennisDatabase.matchDao().getMatches()

    suspend fun updateMatch(matchEntity: MatchEntity) {
        return tennisDatabase.matchDao().updateMatch(matchEntity)
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
        return tennisDatabase.matchDao().insertMatch(match)
    }
}
