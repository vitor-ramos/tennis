package dev.vitorramos.tennis.db

import dev.vitorramos.tennis.db.entity.MatchEntity
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class MatchModel {
    fun getMatch(matchId: Int): Deferred<MatchEntity?> {
        return GlobalScope.async {
            TheDatabase.INSTANCE?.db()?.matchDao()?.getMatch(matchId)
        }
    }
}
