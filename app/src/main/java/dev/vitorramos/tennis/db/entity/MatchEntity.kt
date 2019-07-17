package dev.vitorramos.tennis.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import dev.vitorramos.tennis.Score

@Entity(tableName = "matches")
data class MatchEntity(
    @PrimaryKey var id: Long? = null,

    var started: Long,
    var ended: Long? = null,

    var gamesToSet: Int,
    var setsToMatch: Int,

    var hostName: String = "",
    @TypeConverters(ScoreConverter::class)
    var hostScore: Score = Score(),

    var guestName: String = "",
    @TypeConverters(ScoreConverter::class)
    var guestScore: Score = Score()
)

class ScoreConverter {
    @TypeConverter
    fun toMap(value: Score): HashMap<String, Int> {
        return HashMap<String, Int>().apply {
            put("points", value.points)
            put("games", value.games)
            put("sets", value.sets)
        }
    }

    @TypeConverter
    fun toScore(value: HashMap<String, Int>): Score {
        return Score().apply {
            points = value[FIELD_POINTS]!!
            games = value[FIELD_GAMES]!!
            sets = value[FIELD_SETS]!!
        }
    }

    companion object {
        private const val FIELD_POINTS = "points"
        private const val FIELD_GAMES = "games"
        private const val FIELD_SETS = "sets"
    }
}
