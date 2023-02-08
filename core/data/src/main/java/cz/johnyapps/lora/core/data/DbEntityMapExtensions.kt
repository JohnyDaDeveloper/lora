import cz.johnyapps.lora.core.constants.GamePhases
import cz.johnyapps.lora.core.data.fromCallable
import cz.johnyapps.lora.core.data.game.Game
import cz.johnyapps.lora.core.data.game.GameParseException
import cz.johnyapps.lora.core.data.game.GameSettings
import cz.johnyapps.lora.core.data.game.GameState
import cz.johnyapps.lora.core.data.result
import cz.johnyapps.lora.core.database.game.GameDbEntity
import cz.johnyapps.lora.core.database.game.GameSettingsDbEntity
import cz.johnyapps.lora.core.database.game.GameStateDbEntity

internal fun GameDbEntity.map(): Result<Game> {
    return Result.fromCallable {
        Game(
            uuid = uuid,
            settings = settings.map().getOrThrow(),
            state = state.map().getOrThrow()
        )
    }
}

internal fun GameStateDbEntity.map(): Result<GameState> {
    return Result.fromCallable {
        when (gamePhase) {
            GamePhases.PREPARING -> {
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

internal fun GameSettingsDbEntity.map(): Result<GameSettings> {
    return GameSettings(
        players = players
    ).result()
}
