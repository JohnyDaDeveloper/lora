package cz.johnyapps.lora.core.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SourceModule {

    @Provides
    @Singleton
    fun provideGameNetworkSource(): GameNetworkSource {
        return FakeGameNetworkSource()
    }
}
