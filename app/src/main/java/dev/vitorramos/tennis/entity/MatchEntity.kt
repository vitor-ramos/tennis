package dev.vitorramos.tennis.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "matches")
data class MatchEntity(
    @PrimaryKey var id: Long? = null,

    val started: Long,

    var gamesToSet: Int,

    val hostName: String = "",
    var hostPoints: Int = 0,
    var hostGames: Int = 0,
    var hostSets: Int = 0,

    val guestName: String = "",
    var guestPoints: Int = 0,
    var guestGames: Int = 0,
    var guestSets: Int = 0
) : Serializable
