package dev.vitorramos.tennis.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import dev.vitorramos.tennis.db.entity.GameEntity

@Dao
interface GameDao {
    @Insert
    suspend fun insertGame(game: GameEntity)

    @Query("SELECT * FROM game")
    suspend fun getGames(): Array<GameEntity>
}
