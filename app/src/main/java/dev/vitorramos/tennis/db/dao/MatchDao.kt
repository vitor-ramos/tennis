package dev.vitorramos.tennis.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
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

    @Update
    suspend fun updateMatch(match: MatchEntity)
}
