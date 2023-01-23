package cz.johnyapps.lora.core.database.game

import androidx.room.ColumnInfo
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GameSettingsDbEntity(
    @ColumnInfo("players")
    val players: Int
)