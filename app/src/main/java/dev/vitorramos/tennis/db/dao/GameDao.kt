package dev.vitorramos.tennis.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import dev.vitorramos.tennis.db.entity.GameEntity

@Dao
interface GameDao {
    @Insert
    suspend fun insertGame(game: GameEntity): Long

    @Delete
    suspend fun deleteGame(game: GameEntity)

    @Query("SELECT * FROM games")
    suspend fun getGames(): Array<GameEntity>

    @Query("SELECT * FROM games WHERE id == :gameId")
    suspend fun getGame(gameId: Int): GameEntity?

    @Query(value = "UPDATE games SET hostPoints = :points WHERE id == :gameId")
    suspend fun updateHostPoints(gameId: Int, points: Int)

    @Query(value = "UPDATE games SET hostGames = :games WHERE id == :gameId")
    suspend fun updateHostGames(gameId: Int, games: Int)

    @Query(value = "UPDATE games SET hostSets = :sets WHERE id == :gameId")
    suspend fun updateHostSets(gameId: Int, sets: Int)

    @Query(value = "UPDATE games SET guestPoints = :points WHERE id == :gameId")
    suspend fun updateGuestPoints(gameId: Int, points: Int)

    @Query(value = "UPDATE games SET guestGames = :games WHERE id == :gameId")
    suspend fun updateGuestGames(gameId: Int, games: Int)

    @Query(value = "UPDATE games SET guestSets = :sets WHERE id == :gameId")
    suspend fun updateGuestSets(gameId: Int, sets: Int)
}
