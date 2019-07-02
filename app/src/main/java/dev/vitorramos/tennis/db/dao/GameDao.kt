package dev.vitorramos.tennis.db.dao

import androidx.room.*
import dev.vitorramos.tennis.db.entity.GameEntity

@Dao
interface GameDao {
    @Insert
    suspend fun insertGame(game: GameEntity): Long

    @Query("SELECT * FROM games")
    suspend fun getGames(): Array<GameEntity>

    @Query("SELECT * FROM games WHERE id == :gameId")
    suspend fun getGame(gameId: Long): GameEntity?

    @Update
    suspend fun updateGame(game: GameEntity)

    @Delete
    suspend fun deleteGame(game: GameEntity)
}
