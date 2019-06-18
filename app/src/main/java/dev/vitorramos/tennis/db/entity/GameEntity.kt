package dev.vitorramos.tennis.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date

@Entity(tableName = "game")
data class GameEntity(
    @PrimaryKey var id: Int,

    var started: Date,
    var ended: Date,

    var gamesToSet: Int,
    var setsToMatch: Int,

    var hostName: String,
    var hostPoints: Int,
    var hostGames: Int,
    var hostSets: Int,

    var guestName: String,
    var guestPoints: Int,
    var guestGames: Int,
    var guestSets: Int
)
