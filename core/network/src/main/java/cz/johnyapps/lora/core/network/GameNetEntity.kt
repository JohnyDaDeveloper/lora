package cz.johnyapps.lora.core.network

data class GameNetEntity(
    val uuid: String,
    val settings: GameSettingsNetEntity,
    val state: GameStateNetEntity
)
