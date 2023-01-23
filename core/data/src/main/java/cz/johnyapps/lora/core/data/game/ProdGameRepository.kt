package cz.johnyapps.lora.core.data.game

import cz.johnyapps.lora.core.data.map
import cz.johnyapps.lora.core.data.mapToDb
import cz.johnyapps.lora.core.data.mapToNet
import cz.johnyapps.lora.core.database.game.GameDao
import cz.johnyapps.lora.core.network.GameNetworkSource

internal class ProdGameRepository(
    private val gameDao: GameDao,
    private val gameNetworkSource: GameNetworkSource
) : GameRepository {
    override suspend fun createGame(game: Game) {
        gameNetworkSource.createGame(game.mapToNet().getOrThrow())
        gameDao.insert(game.mapToDb().getOrThrow())
    }

    override suspend fun saveGame(game: Game) {
        gameDao.insert(game.mapToDb().getOrThrow())
    }

    override suspend fun getGame(uuid: String): Game {
        return gameDao.get(uuid).map().getOrThrow()
    }
}