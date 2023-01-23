package cz.johnyapps.lora.core.network

import kotlinx.coroutines.flow.Flow

interface GameNetworkSource {
    suspend fun createGame(gameNetEntity: GameNetEntity)

    suspend fun updateGame(gameNetEntity: GameNetEntity)

    fun getGameUpdates(uuid: String): Flow<GameNetEntity>
}