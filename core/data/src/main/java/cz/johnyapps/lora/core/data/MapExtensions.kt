package cz.johnyapps.lora.core.data

import cz.johnyapps.lora.core.data.game.Game
import cz.johnyapps.lora.core.data.game.GameParseException
import cz.johnyapps.lora.core.data.game.GamePhases
import cz.johnyapps.lora.core.data.game.GameSettings
import cz.johnyapps.lora.core.data.game.GameState
import cz.johnyapps.lora.core.database.game.GameDbEntity
import cz.johnyapps.lora.core.database.game.GameSettingsDbEntity
import cz.johnyapps.lora.core.database.game.GameStateDbEntity
import cz.johnyapps.lora.core.network.GameNetEntity
import cz.johnyapps.lora.core.network.GameSettingsNetEntity
import cz.johnyapps.lora.core.network.GameStateNetEntity

internal fun GameDbEntity.map(): Result<Game> {
    return Result.fromCallable {
        Game(
            uuid = uuid,
            settings = settings.map().getOrThrow(),
            state = state.map().getOrThrow()
        )
    }
}

internal fun GameNetEntity.map(): Result<Game> {
    return Result.fromCallable {
        Game(
            uuid = uuid,
            settings = settings.map().getOrThrow(),
            state = state.map().getOrThrow()
        )
    }
}

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

internal fun GameStateDbEntity.map(): Result<GameState> {
    return Result.fromCallable {
        when (gamePhase) {
            GamePhases.PREPARATION -> {
                GameState.Preparing
            }

            GamePhases.PLAYING -> {
                if (playerOrder.isEmpty()) {
                    throw GameParseException("Unexpected empty player order")
                }

                if (playingPlayer.isEmpty()) {
                    throw GameParseException("Playing player can't be empty")
                }

                GameState.Playing(
                    playingPlayer = playingPlayer,
                    playerOrder = playerOrder
                )
            }

            GamePhases.FINISHED -> {
                if (playerOrder.isEmpty()) {
                    throw GameParseException("Unexpected empty end game player order")
                }

                GameState.Finished(
                    playerOrder = endGameOrder
                )
            }

            else -> throw GameParseException(
                "Failed to parse game phase, unexpected number $gamePhase"
            )
        }
    }
}

internal fun GameStateNetEntity.map(): Result<GameState> {
    return Result.fromCallable {
        when (gamePhase) {
            GamePhases.PREPARATION -> {
                GameState.Preparing
            }

            GamePhases.PLAYING -> {
                if (playerOrder.isEmpty()) {
                    throw GameParseException("Unexpected empty player order")
                }

                if (playingPlayer.isEmpty()) {
                    throw GameParseException("Playing player can't be empty")
                }

                GameState.Playing(
                    playingPlayer = playingPlayer,
                    playerOrder = playerOrder
                )
            }

            GamePhases.FINISHED -> {
                if (playerOrder.isEmpty()) {
                    throw GameParseException("Unexpected empty end game player order")
                }

                GameState.Finished(
                    playerOrder = endGameOrder
                )
            }

            else -> throw GameParseException(
                "Failed to parse game phase, unexpected number $gamePhase"
            )
        }
    }
}

internal fun GameState.mapToDb(): Result<GameStateDbEntity> {
    return Result.fromCallable {
        when (this) {
            is GameState.Preparing -> {
                GameStateDbEntity(
                    gamePhase = GamePhases.PREPARATION,
                    playingPlayer = "",
                    playerOrder = emptyMap(),
                    endGameOrder = emptyMap()
                )
            }

            is GameState.Playing -> {
                GameStateDbEntity(
                    gamePhase = GamePhases.PLAYING,
                    playingPlayer = playingPlayer,
                    playerOrder = playerOrder,
                    endGameOrder = emptyMap()
                )
            }

            is GameState.Finished -> {
                GameStateDbEntity(
                    gamePhase = GamePhases.FINISHED,
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
                    gamePhase = GamePhases.PREPARATION,
                    playingPlayer = "",
                    playerOrder = emptyMap(),
                    endGameOrder = emptyMap()
                )
            }

            is GameState.Playing -> {
                GameStateNetEntity(
                    gamePhase = GamePhases.PLAYING,
                    playingPlayer = playingPlayer,
                    playerOrder = playerOrder,
                    endGameOrder = emptyMap()
                )
            }

            is GameState.Finished -> {
                GameStateNetEntity(
                    gamePhase = GamePhases.FINISHED,
                    playingPlayer = "",
                    playerOrder = emptyMap(),
                    endGameOrder = playerOrder
                )
            }
        }
    }
}

internal fun GameSettingsDbEntity.map(): Result<GameSettings> {
    return GameSettings(
        players = players
    ).result()
}

internal fun GameSettingsNetEntity.map(): Result<GameSettings> {
    return GameSettings(
        players = players
    ).result()
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