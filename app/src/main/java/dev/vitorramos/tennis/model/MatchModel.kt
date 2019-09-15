package dev.vitorramos.tennis.model

import dev.vitorramos.tennis.TennisDatabase
import dev.vitorramos.tennis.entity.MatchEntity

class MatchModel(private val tennisDatabase: TennisDatabase) {
    fun getMatch(matchId: Long) = tennisDatabase.matchDao().getMatch(matchId)


    fun getMatches() = tennisDatabase.matchDao().getMatches()

    suspend fun updateMatch(matchEntity: MatchEntity) {
        tennisDatabase.matchDao().updateMatch(matchEntity)
    }

    suspend fun insertMatch(
        started: Long,
        gamesToSet: Int,
        hostName: String = "",
        guestName: String = ""
    ): Long? {
        val match = MatchEntity(
            started = started,
            gamesToSet = gamesToSet,
            hostName = hostName,
            guestName = guestName
        )
        return tennisDatabase.matchDao().insertMatch(match)
    }

    fun deleteMatch(matchId: Long) {
        tennisDatabase.matchDao().deleteMatch(matchId)
    }
}
