package dev.vitorramos.tennis

import android.app.Application
import androidx.room.Room
import dev.vitorramos.tennis.model.MatchModel
import dev.vitorramos.tennis.repository.TennisRepository

class TennisApplication : Application() {
    lateinit var tennisRepository: TennisRepository

    override fun onCreate() {
        super.onCreate()

        val db = Room.databaseBuilder(
            applicationContext,
            TennisDatabase::class.java,
            DATABASE_NAME
        ).build()
        tennisRepository = TennisRepository(MatchModel(db))
    }

    companion object {
        private const val DATABASE_NAME = "tennis-database"
    }
}
