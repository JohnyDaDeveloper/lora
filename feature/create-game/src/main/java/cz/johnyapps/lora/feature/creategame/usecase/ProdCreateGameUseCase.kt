package cz.johnyapps.lora.feature.creategame.usecase

import cz.johnyapps.lora.core.data.game.Game
import cz.johnyapps.lora.core.data.game.GameRepository
import cz.johnyapps.lora.core.data.game.GameSettings
import cz.johnyapps.lora.core.data.game.GameState
import java.util.UUID

class ProdCreateGameUseCase(
    private val gameRepository: GameRepository
) : CreateGameUseCase {

    override suspend operator fun invoke(
        players: Int
    ) {
        gameRepository.createGame(
            Game(
                uuid = generateUuid(),
                settings = GameSettings(
                    players = players
                ),
                state = GameState.Preparing
            )
        )
    }

    private fun generateUuid(): String {
        return UUID.randomUUID().toString()
    }
}