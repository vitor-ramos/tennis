package dev.vitorramos.tennis.db

import dev.vitorramos.tennis.db.entity.MatchEntity

class MatchModel(private val theDatabase: TheDatabase) {
    suspend fun getMatch(matchId: Long): MatchEntity? {
        return theDatabase.matchDao().getMatch(matchId)

    }

    suspend fun insertMatch(
        started: Long,
        ended: Long? = null,
        gamesToSet: Int,
        setsToMatch: Int,
        hostName: String = "",
        hostPoints: Int = 0,
        hostGames: Int = 0,
        hostSets: Int = 0,

        guestName: String = "",
        guestPoints: Int = 0,
        guestGames: Int = 0,
        guestSets: Int = 0
    ): Long? {
        val match = MatchEntity(
            started = started,
            ended = ended,
            gamesToSet = gamesToSet,
            setsToMatch = setsToMatch,
            hostName = hostName,
            hostPoints = hostPoints,
            hostGames = hostGames,
            hostSets = hostSets,
            guestName = guestName,
            guestPoints = guestPoints,
            guestGames = guestGames,
            guestSets = guestSets
        )
        return theDatabase.matchDao().insertMatch(match)
    }
}
