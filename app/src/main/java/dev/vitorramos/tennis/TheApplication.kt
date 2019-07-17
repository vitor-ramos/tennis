package dev.vitorramos.tennis

import android.app.Application
import androidx.room.Room
import dev.vitorramos.tennis.db.MatchModel
import dev.vitorramos.tennis.db.TheDatabase

class TheApplication : Application() {
    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        val db = Room.databaseBuilder(
            applicationContext,
            TheDatabase::class.java,
            DATABASE_NAME
        ).build()

        component = DaggerApplicationComponent
            .builder()
            .applicationModule(
                ApplicationModule(
                    TheRepository(),
                    MatchModel(),
                    db
                )
            )
            .build()
    }

    companion object {
        private const val DATABASE_NAME = "the-database"
    }
}
