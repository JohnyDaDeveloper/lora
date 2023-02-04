package cz.johnyapps.lora.core.database.game

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GameStateDbEntity(
    val gamePhase: String,
    val playingPlayer: String,
    val playerOrder: Map<Int, String>,
    val endGameOrder: Map<Int, String>
)
