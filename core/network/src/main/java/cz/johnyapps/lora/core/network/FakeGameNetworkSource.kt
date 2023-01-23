package cz.johnyapps.lora.core.network

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onStart

internal class FakeGameNetworkSource(

) : GameNetworkSource {
    private val gameMap: MutableMap<String, GameNetEntity> = mutableMapOf()
    private val gameUpdates = MutableSharedFlow<GameNetEntity>()

    override suspend fun createGame(gameNetEntity: GameNetEntity) {
        gameMap[gameNetEntity.uuid] = gameNetEntity
        gameUpdates.emit(gameNetEntity)
    }

    override suspend  fun updateGame(gameNetEntity: GameNetEntity) {
        gameMap[gameNetEntity.uuid] = gameNetEntity
        gameUpdates.emit(gameNetEntity)
    }

    override fun getGameUpdates(uuid: String): Flow<GameNetEntity> {
        return gameUpdates.onStart {
            val first = gameMap[uuid]

            if (first != null) {
                flowOf(first)
            } else {
                emptyFlow()
            }
        }
    }
}