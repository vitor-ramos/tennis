package dev.vitorramos.tennis.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import dev.vitorramos.tennis.entity.MatchEntity

@Dao
interface MatchDao {
    @Insert
    suspend fun insertMatch(match: MatchEntity)

    @Query("DELETE FROM matches WHERE id == :matchId")
    fun deleteMatch(matchId: Long)

    @Query("SELECT * FROM matches ORDER BY started DESC")
    fun getMatches(): LiveData<List<MatchEntity?>?>?

    @Query("SELECT * FROM matches WHERE id == :matchId")
    fun getMatch(matchId: Long): LiveData<MatchEntity?>?

    @Update
    suspend fun updateMatch(match: MatchEntity)
}
