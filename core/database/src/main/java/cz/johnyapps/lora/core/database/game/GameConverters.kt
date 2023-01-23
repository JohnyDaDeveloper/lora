package cz.johnyapps.lora.core.database.game

import androidx.room.TypeConverter
import com.squareup.moshi.Moshi
import java.text.ParseException

object GameConverters {
    private val moshi: Moshi by lazy {
        Moshi.Builder().build()
    }

    private val gameSettingsAdapter by lazy {
        moshi.adapter(GameSettingsDbEntity::class.java)
    }

    private val gameStateAdapter by lazy {
        moshi.adapter(GameStateDbEntity::class.java)
    }

    @TypeConverter
    fun gameSettingsToString(
        settings: GameSettingsDbEntity
    ): String {
        return gameSettingsAdapter.toJson(settings)
    }

    @TypeConverter
    fun stringToGameSettings(
        string: String
    ): GameSettingsDbEntity {
        return gameSettingsAdapter.fromJson(string) ?:
        throw ParseException(
            "Failed to parse $string as ${GameSettingsDbEntity::class.simpleName}",
            0
        )
    }

    @TypeConverter
    fun gameStateToString(
        state: GameStateDbEntity
    ): String {
        return gameStateAdapter.toJson(state)
    }

    @TypeConverter
    fun stringToGameState(
        string: String
    ): GameStateDbEntity {
        return gameStateAdapter.fromJson(string) ?:
        throw ParseException(
            "Failed to parse $string as ${GameStateDbEntity::class.simpleName}",
            0
        )
    }
}