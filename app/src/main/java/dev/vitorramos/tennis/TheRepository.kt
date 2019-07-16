package dev.vitorramos.tennis

import dev.vitorramos.tennis.db.MatchModel
import dev.vitorramos.tennis.db.entity.MatchEntity
import kotlinx.coroutines.Deferred

class TheRepository {
    companion object {
        fun getMatch(matchId: Int): Deferred<MatchEntity?> {
            return MatchModel.getMatch(matchId)
        }

        fun insertMatch(
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
        ) {
            MatchModel.insertMatch(
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
}
