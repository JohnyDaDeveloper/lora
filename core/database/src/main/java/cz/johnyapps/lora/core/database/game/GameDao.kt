package cz.johnyapps.lora.core.database.game

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface GameDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(gameDbEntity: GameDbEntity): Long

    @Query("SELECT * FROM games WHERE uuid = :uuid")
    suspend fun get(uuid: String): GameDbEntity

    @Query("DELETE FROM games WHERE uuid = :uuid")
    suspend fun delete(uuid: String)
}