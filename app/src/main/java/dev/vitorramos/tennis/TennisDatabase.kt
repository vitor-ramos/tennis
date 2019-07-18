package dev.vitorramos.tennis

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.vitorramos.tennis.dao.MatchDao
import dev.vitorramos.tennis.entity.MatchEntity

@Database(entities = [MatchEntity::class], version = 1)
abstract class TennisDatabase : RoomDatabase() {
    abstract fun matchDao(): MatchDao
}
