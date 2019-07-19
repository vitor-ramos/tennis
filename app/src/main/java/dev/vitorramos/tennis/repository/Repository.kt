package dev.vitorramos.tennis.repository

import dev.vitorramos.tennis.entity.MatchEntity
import dev.vitorramos.tennis.model.MatchModel

class Repository private constructor(private val matchModel: MatchModel) {
    fun getMatch(matchId: Long) = matchModel.getMatch(matchId)

    fun getMatches() = matchModel.getMatches()

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

    companion object {
        fun init(matchModel: MatchModel) {
            it = Repository(matchModel)
        }

        var it: Repository? = null
    }
}
