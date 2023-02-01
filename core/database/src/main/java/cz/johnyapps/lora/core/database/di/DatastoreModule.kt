package cz.johnyapps.lora.core.database.di

import android.content.Context
import androidx.room.Room
import cz.johnyapps.lora.core.database.LoraRoomDatabase
import cz.johnyapps.lora.core.database.game.GameDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatastoreModule {
    @Provides
    @Singleton
    fun provideGameDao(
        db: LoraRoomDatabase
    ): GameDao {
        return db.gameDao()
    }

    @Provides
    @Singleton
    fun provideLoraRoomDatabase(
        @ApplicationContext context: Context
    ): LoraRoomDatabase {
        return Room.databaseBuilder(
            context,
            LoraRoomDatabase::class.java,
            LoraRoomDatabase.DB_NAME
        ).build()
    }
}
