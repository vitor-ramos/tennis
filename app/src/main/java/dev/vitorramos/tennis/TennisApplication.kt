package dev.vitorramos.tennis

import android.app.Application
import androidx.room.Room
import dev.vitorramos.tennis.model.MatchModel
import dev.vitorramos.tennis.repository.MatchRepository

class TennisApplication : Application() {
    lateinit var matchRepository: MatchRepository

    override fun onCreate() {
        super.onCreate()

        val db = Room.databaseBuilder(
            applicationContext,
            TheDatabase::class.java,
            DATABASE_NAME
        ).build()
        matchRepository = MatchRepository(MatchModel(db))
    }

    companion object {
        private const val DATABASE_NAME = "the-database"
    }
}
