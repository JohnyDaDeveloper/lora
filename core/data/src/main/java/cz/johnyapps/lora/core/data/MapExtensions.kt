package cz.johnyapps.lora.core.data

import cz.johnyapps.lora.core.data.game.Game
import cz.johnyapps.lora.core.data.game.GameSettings
import cz.johnyapps.lora.core.data.game.GameState
import cz.johnyapps.lora.core.database.game.GameDbEntity
import cz.johnyapps.lora.core.database.game.GameSettingsDbEntity
import cz.johnyapps.lora.core.database.game.GameStateDbEntity
import cz.johnyapps.lora.core.network.GameNetEntity
import cz.johnyapps.lora.core.network.GameSettingsNetEntity
import cz.johnyapps.lora.core.network.GameStateNetEntity

internal fun Game.mapToDb(): Result<GameDbEntity> {
    return Result.fromCallable {
        GameDbEntity(
            uuid = uuid,
            settings = settings.mapToDb().getOrThrow(),
            state = state.mapToDb().getOrThrow()
        )
    }
}

internal fun Game.mapToNet(): Result<GameNetEntity> {
    return Result.fromCallable {
        GameNetEntity(
            uuid = uuid,
            settings = settings.mapToNet().getOrThrow(),
            state = state.mapToNet().getOrThrow()
        )
    }
}

internal fun GameState.mapToDb(): Result<GameStateDbEntity> {
    return Result.fromCallable {
        when (this) {
            is GameState.Preparing -> {
                GameStateDbEntity(
                    gamePhase = cz.johnyapps.lora.core.constants.GamePhases.INIT,
                    playingPlayer = "",
                    playerOrder = emptyMap(),
                    endGameOrder = emptyMap()
                )
            }

            is GameState.Playing -> {
                GameStateDbEntity(
                    gamePhase = cz.johnyapps.lora.core.constants.GamePhases.PLAYING,
                    playingPlayer = playingPlayer,
                    playerOrder = playerOrder,
                    endGameOrder = emptyMap()
                )
            }

            is GameState.Finished -> {
                GameStateDbEntity(
                    gamePhase = cz.johnyapps.lora.core.constants.GamePhases.FINISHED,
                    playingPlayer = "",
                    playerOrder = emptyMap(),
                    endGameOrder = playerOrder
                )
            }
        }
    }
}

internal fun GameState.mapToNet(): Result<GameStateNetEntity> {
    return Result.fromCallable {
        when (this) {
            is GameState.Preparing -> {
                GameStateNetEntity(
                    gamePhase = cz.johnyapps.lora.core.constants.GamePhases.INIT,
                    playingPlayer = "",
                    playerOrder = emptyMap(),
                    endGameOrder = emptyMap()
                )
            }

            is GameState.Playing -> {
                GameStateNetEntity(
                    gamePhase = cz.johnyapps.lora.core.constants.GamePhases.PLAYING,
                    playingPlayer = playingPlayer,
                    playerOrder = playerOrder,
                    endGameOrder = emptyMap()
                )
            }

            is GameState.Finished -> {
                GameStateNetEntity(
                    gamePhase = cz.johnyapps.lora.core.constants.GamePhases.FINISHED,
                    playingPlayer = "",
                    playerOrder = emptyMap(),
                    endGameOrder = playerOrder
                )
            }
        }
    }
}

internal fun GameSettings.mapToDb(): Result<GameSettingsDbEntity> {
    return GameSettingsDbEntity(
        players = players
    ).result()
}

internal fun GameSettings.mapToNet(): Result<GameSettingsNetEntity> {
    return GameSettingsNetEntity(
        players = players
    ).result()
}
