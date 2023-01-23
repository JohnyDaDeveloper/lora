package cz.johnyapps.lora.core.data.game

interface GameRepository {
    /**
     * Creates game
     * @param game Game that should be created
     */
    suspend fun createGame(game: Game)

    /**
     * Saves game
     * @param game Game that should be saved
     */
    suspend fun saveGame(game: Game)

    /**
     * Returns game by its UUID
     * @return [Game]
     */
    suspend fun getGame(uuid: String): Game
}