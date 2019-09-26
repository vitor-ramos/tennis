package dev.vitorramos.tennis.repository

import dev.vitorramos.tennis.entity.MatchEntity
import dev.vitorramos.tennis.model.MatchModel

class Repository private constructor(private val matchModel: MatchModel) {
    fun getMatches() = matchModel.getMatches()

    suspend fun updateMatch(matchEntity: MatchEntity) = matchModel.updateMatch(matchEntity)

    suspend fun insertMatch(
        started: Long,
        gamesToSet: Int,
        hostName: String = "",
        guestName: String = ""
    ): Long? {
        return matchModel.insertMatch(
            started = started,
            gamesToSet = gamesToSet,
            hostName = hostName,
            guestName = guestName
        )
    }

    fun deleteMatch(matchId: Long) = matchModel.deleteMatch(matchId)

    companion object {
        fun init(matchModel: MatchModel) {
            it = Repository(matchModel)
        }

        var it: Repository? = null
    }
}
