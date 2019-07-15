package dev.vitorramos.tennis

import dev.vitorramos.tennis.db.MatchModel
import dev.vitorramos.tennis.db.entity.MatchEntity
import kotlinx.coroutines.Deferred

class TheRepository {
    private lateinit var matchModel: MatchModel
    fun getMatch(matchId: Int): Deferred<MatchEntity?> {
        return matchModel.getMatch(matchId)
    }
}
