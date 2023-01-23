package cz.johnyapps.lora.feature.creategame.usecase

interface CreateGameUseCase {
    suspend operator fun invoke(players: Int)
}