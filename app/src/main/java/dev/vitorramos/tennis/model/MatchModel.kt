package dev.vitorramos.tennis.model

import dev.vitorramos.tennis.TennisDatabase
import dev.vitorramos.tennis.entity.MatchEntity

class MatchModel(private val tennisDatabase: TennisDatabase) {
    fun getMatches() = tennisDatabase.matchDao().getMatches()

    suspend fun updateMatch(matchEntity: MatchEntity) {
        tennisDatabase.matchDao().updateMatch(matchEntity)
    }

    suspend fun insertMatch(
        started: Long,
        gamesToSet: Int,
        hostName: String = "",
        guestName: String = ""
    ) = tennisDatabase.matchDao().insertMatch(
        MatchEntity(
            started = started,
            gamesToSet = gamesToSet,
            hostName = hostName,
            guestName = guestName
        )
    )

    fun deleteMatch(matchId: Long) {
        tennisDatabase.matchDao().deleteMatch(matchId)
    }
}
