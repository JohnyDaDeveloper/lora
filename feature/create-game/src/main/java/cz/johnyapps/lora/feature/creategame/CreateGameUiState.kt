package cz.johnyapps.lora.feature.creategame

data class CreateGameUiState(
    val playerCount: PlayerCount = PlayerCount.Four,
    val showWorking: Boolean = false
)
