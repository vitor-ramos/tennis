package dev.vitorramos.tennis

import android.content.Context
import dev.vitorramos.tennis.db.TheDatabase
import dev.vitorramos.tennis.db.entity.MatchEntity
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class TheRepository {
    private lateinit var applicationContext: Context

    fun getMatch(matchId: Int): Deferred<MatchEntity?> {
        return GlobalScope.async {
            TheDatabase.db(applicationContext).matchDao().getMatch(matchId)
        }
    }
}
