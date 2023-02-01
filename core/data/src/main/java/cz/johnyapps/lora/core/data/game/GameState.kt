package cz.johnyapps.lora.core.data.game

sealed class GameState {
    object Preparing : GameState()

    data class Playing(
        val playingPlayer: String,
        val playerOrder: Map<Int, String>
    ) : GameState()

    data class Finished(
        val playerOrder: Map<Int, String>
    ) : GameState()
}
