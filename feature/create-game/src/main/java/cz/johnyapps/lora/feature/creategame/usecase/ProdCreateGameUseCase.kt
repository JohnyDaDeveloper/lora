package cz.johnyapps.lora.feature.creategame.usecase

import cz.johnyapps.lora.core.data.game.GameRepository
import cz.johnyapps.lora.core.data.game.GameSettings
import java.util.UUID

class ProdCreateGameUseCase(
    private val gameRepository: GameRepository
) : CreateGameUseCase {

    override suspend operator fun invoke(
        players: Int
    ): String {
        return gameRepository.createGame(
            GameSettings(players = players)
        )
    }

    private fun generateUuid(): String {
        return UUID.randomUUID().toString()
    }
}