package dev.vitorramos.tennis.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "matches")
data class MatchEntity(
    @PrimaryKey var id: Long? = null,

    var started: Long,
    var ended: Long? = null,

    var gamesToSet: Int,
    var setsToMatch: Int,

    var hostName: String = "",
    var hostPoints: Int = 0,
    var hostGames: Int = 0,
    var hostSets: Int = 0,

    var guestName: String = "",
    var guestPoints: Int = 0,
    var guestGames: Int = 0,
    var guestSets: Int = 0
)
