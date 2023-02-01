package cz.johnyapps.lora.feature.creategame.usecase

import cz.johnyapps.lora.core.data.game.GameRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    fun provideCreateGameUseCase(
        gameRepository: GameRepository
    ): CreateGameUseCase {
        return ProdCreateGameUseCase(
            gameRepository
        )
    }
}
