package cz.johnyapps.lora.core.data

import cz.johnyapps.lora.core.data.game.GameRepository
import cz.johnyapps.lora.core.data.game.ProdGameRepository
import cz.johnyapps.lora.core.database.game.GameDao
import cz.johnyapps.lora.core.network.GameNetworkSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideGameRepository(
        gameDao: GameDao,
        gameNetworkSource: GameNetworkSource
    ): GameRepository {
        return ProdGameRepository(
            gameDao,
            gameNetworkSource
        )
    }
}
