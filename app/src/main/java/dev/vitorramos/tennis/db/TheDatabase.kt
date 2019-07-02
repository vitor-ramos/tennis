package dev.vitorramos.tennis.db

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.vitorramos.tennis.db.dao.GameDao
import dev.vitorramos.tennis.db.entity.GameEntity

@Database(entities = [GameEntity::class], version = 1)
abstract class TheDatabase : RoomDatabase() {
    abstract fun gameDao(): GameDao
}
