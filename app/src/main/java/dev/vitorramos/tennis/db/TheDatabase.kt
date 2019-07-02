package dev.vitorramos.tennis.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dev.vitorramos.tennis.db.dao.GameDao
import dev.vitorramos.tennis.db.entity.GameEntity

@Database(entities = [GameEntity::class], version = 1)
abstract class TheDatabase : RoomDatabase() {
    abstract fun gameDao(): GameDao

    companion object {
        private var INSTANCE: TheDatabase? = null

        fun db(applicationContext: Context): TheDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    applicationContext,
                    TheDatabase::class.java,
                    DATABASE_NAME
                ).build()
            }

            return INSTANCE!!
        }

        private const val DATABASE_NAME = "the-database"
    }
}
