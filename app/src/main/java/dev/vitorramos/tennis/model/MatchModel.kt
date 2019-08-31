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
        gamesToSet: Int,
        setsToMatch: Int,
        hostName: String = "",
        guestName: String = ""
    ): Long? {
        val match = MatchEntity(
            gamesToSet = gamesToSet,
            setsToMatch = setsToMatch,
            hostName = hostName,
            guestName = guestName
        )
        return tennisDatabase.matchDao().insertMatch(match)
    }
}
