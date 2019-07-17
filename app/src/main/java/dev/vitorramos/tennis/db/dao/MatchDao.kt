package dev.vitorramos.tennis.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
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
    suspend fun getMatch(matchId: Long): MatchEntity?

    @Query(value = "UPDATE matches SET hostPoints = :points WHERE id == :matchId")
    suspend fun updateHostPoints(matchId: Int, points: Int)

    @Query(value = "UPDATE matches SET hostGames = :games WHERE id == :matchId")
    suspend fun updateHostGames(matchId: Int, games: Int)

    @Query(value = "UPDATE matches SET hostSets = :sets WHERE id == :matchId")
    suspend fun updateHostSets(matchId: Int, sets: Int)

    @Query(value = "UPDATE matches SET guestPoints = :points WHERE id == :matchId")
    suspend fun updateGuestPoints(matchId: Int, points: Int)

    @Query(value = "UPDATE matches SET guestGames = :games WHERE id == :matchId")
    suspend fun updateGuestGames(matchId: Int, games: Int)

    @Query(value = "UPDATE matches SET guestSets = :sets WHERE id == :matchId")
    suspend fun updateGuestSets(matchId: Int, sets: Int)
}
