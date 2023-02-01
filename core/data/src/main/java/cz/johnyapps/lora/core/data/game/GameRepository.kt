package cz.johnyapps.lora.core.data.game

interface GameRepository {
    /**
     * Creates game
     * @param gameSettings Game settings which should be used for game creation. Final decision will
     * be on the server, so the settings may be different in the actual game.
     * @return String UUID of created game
     */
    suspend fun createGame(gameSettings: GameSettings): String

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
