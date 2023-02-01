package cz.johnyapps.lora.core.database.game

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "games")
data class GameDbEntity(
    @PrimaryKey(autoGenerate = false)
    val uuid: String,
    @Embedded("settings_")
    val settings: GameSettingsDbEntity,
    @ColumnInfo("state")
    val state: GameStateDbEntity
)
