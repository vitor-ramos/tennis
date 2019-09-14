package dev.vitorramos.tennis.repository

import dev.vitorramos.tennis.entity.MatchEntity
import dev.vitorramos.tennis.model.MatchModel

class Repository private constructor(private val matchModel: MatchModel) {
    fun getMatch(matchId: Long) = matchModel.getMatch(matchId)

    fun getMatches() = matchModel.getMatches()

    suspend fun updateMatch(matchEntity: MatchEntity) = matchModel.updateMatch(matchEntity)

    suspend fun insertMatch(
        started: Long,
        gamesToSet: Int,
        setsToMatch: Int,
        hostName: String = "",
        guestName: String = ""
    ): Long? {
        return matchModel.insertMatch(
            started = started,
            gamesToSet = gamesToSet,
            setsToMatch = setsToMatch,
            hostName = hostName,
            guestName = guestName
        )
    }

    fun deleteMatch(matchId: Long) = matchModel.deleteMatch(matchId)

    suspend fun undoDeletion(matchEntity: MatchEntity) = matchModel.undoDeletion(matchEntity)

    companion object {
        fun init(matchModel: MatchModel) {
            it = Repository(matchModel)
        }

        var it: Repository? = null
    }
}
