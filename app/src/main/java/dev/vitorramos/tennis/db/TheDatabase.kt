package dev.vitorramos.tennis.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dev.vitorramos.tennis.db.dao.MatchDao
import dev.vitorramos.tennis.db.entity.MatchEntity
import javax.inject.Inject

@Database(entities = [MatchEntity::class], version = 1)
abstract class TheDatabase : RoomDatabase() {
    abstract fun matchDao(): MatchDao
    @Inject
    lateinit var applicationContext: Context

    fun db(): TheDatabase {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(
                applicationContext,
                TheDatabase::class.java,
                DATABASE_NAME
            ).build()
        }

        return INSTANCE!!
    }

    companion object {
        var INSTANCE: TheDatabase? = null
        private const val DATABASE_NAME = "the-database"
    }
}
