package cz.johnyapps.lora.core.network

data class GameStateNetEntity(
    val gamePhase: Int,
    val playingPlayer: String,
    val playerOrder: Map<Int, String>,
    val endGameOrder: Map<Int, String>
)