package dev.vitorramos.tennis.db

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.vitorramos.tennis.db.dao.MatchDao
import dev.vitorramos.tennis.db.entity.MatchEntity

@Database(entities = [MatchEntity::class], version = 1)
abstract class TheDatabase : RoomDatabase() {
    abstract fun matchDao(): MatchDao
}
