package dev.vitorramos.tennis

import dev.vitorramos.tennis.db.MatchModel
import dev.vitorramos.tennis.db.entity.MatchEntity
import kotlinx.coroutines.Deferred

class TheRepository(private val matchModel: MatchModel) {
    fun getMatch(matchId: Int): Deferred<MatchEntity?> {
        return matchModel.getMatch(matchId)
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
        return matchModel.insertMatch(
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
    }
}
