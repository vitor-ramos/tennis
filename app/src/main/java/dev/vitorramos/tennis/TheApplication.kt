package dev.vitorramos.tennis

import android.app.Application
import androidx.room.Room
import dev.vitorramos.tennis.db.MatchModel
import dev.vitorramos.tennis.db.TheDatabase

class TheApplication : Application() {
    lateinit var theRepository: TheRepository

    override fun onCreate() {
        super.onCreate()

        val db = Room.databaseBuilder(
            applicationContext,
            TheDatabase::class.java,
            DATABASE_NAME
        ).build()
        theRepository = TheRepository(MatchModel(db))
    }

    companion object {
        private const val DATABASE_NAME = "the-database"
    }
}
