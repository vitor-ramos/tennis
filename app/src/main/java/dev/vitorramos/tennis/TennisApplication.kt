package dev.vitorramos.tennis

import android.app.Application
import androidx.room.Room
import dev.vitorramos.tennis.model.MatchModel
import dev.vitorramos.tennis.repository.Repository
import net.danlew.android.joda.JodaTimeAndroid

@Suppress("unused") // Used in Manifest.xml
class TennisApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        JodaTimeAndroid.init(this)

        val db = Room.databaseBuilder(
            applicationContext,
            TennisDatabase::class.java,
            DATABASE_NAME
        ).build()
        Repository.init(MatchModel(db))
    }

    companion object {
        private const val DATABASE_NAME = "tennis-database"
    }
}
