package cz.johnyapps.lora.core.data.game

data class Game(
    val uuid: String,
    val settings: GameSettings,
    val state: GameState
)