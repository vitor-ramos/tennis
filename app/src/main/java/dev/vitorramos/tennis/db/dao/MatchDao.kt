package dev.vitorramos.tennis.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import dev.vitorramos.tennis.Score
import dev.vitorramos.tennis.db.entity.MatchEntity

@Dao
interface MatchDao {
    @Insert
    suspend fun insertMatch(match: MatchEntity): Long

    @Delete
    suspend fun deleteMatch(match: MatchEntity)

    @Query("SELECT * FROM matches")
    suspend fun getMatches(): Array<MatchEntity>

    @Query("SELECT * FROM matches WHERE id == :matchId")
    fun getMatch(matchId: Long): LiveData<MatchEntity?>?

    @Query(value = "UPDATE matches SET hostScore = :score WHERE id == :matchId")
    suspend fun updateHostScore(matchId: Long, score: Score)

    @Query(value = "UPDATE matches SET guestScore = :score WHERE id == :matchId")
    suspend fun updateGuestScore(matchId: Long, score: Score)
}
