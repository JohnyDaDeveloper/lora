package cz.johnyapps.lora.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import cz.johnyapps.lora.core.database.game.GameConverters
import cz.johnyapps.lora.core.database.game.GameDao
import cz.johnyapps.lora.core.database.game.GameDbEntity

@Database(
    entities = [
        GameDbEntity::class
    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(GameConverters::class)
abstract class LoraRoomDatabase : RoomDatabase() {
    abstract fun gameDao(): GameDao

    companion object {
        const val DB_NAME = "lora-room-database"
    }
}
