package cz.johnyapps.lora.core.data.game

import cz.johnyapps.lora.core.constants.GamePhases
import cz.johnyapps.lora.core.database.game.GameDao
import cz.johnyapps.lora.core.database.game.GameDbEntity
import cz.johnyapps.lora.core.database.game.GameSettingsDbEntity
import cz.johnyapps.lora.core.database.game.GameStateDbEntity
import cz.johnyapps.lora.core.network.GameNetEntity
import cz.johnyapps.lora.core.network.GameNetworkSource
import cz.johnyapps.lora.core.network.GameSettingsNetEntity
import cz.johnyapps.lora.core.network.GameStateNetEntity
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.mockk.MockKAnnotations
import io.mockk.called
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.coVerifyAll
import io.mockk.coVerifyOrder
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

private const val GAME_UUID = "SomeCoolRoom1"
private const val GAME_PLAYERS = 3

internal class ProdGameRepositoryTest {
    @MockK
    lateinit var gameDao: GameDao

    @MockK
    lateinit var gameNetworkSource: GameNetworkSource

    private lateinit var gameRepository: GameRepository

    private val gameSettings = GameSettings(players = GAME_PLAYERS)
    private val game = Game(
        uuid = GAME_UUID,
        settings = gameSettings,
        state = GameState.Preparing
    )

    private val gameNetEntity = GameNetEntity(
        uuid = GAME_UUID,
        settings = GameSettingsNetEntity(
            players = GAME_PLAYERS
        ),
        state = GameStateNetEntity(
            gamePhase = GamePhases.PREPARING,
            playingPlayer = "",
            playerOrder = emptyMap(),
            endGameOrder = emptyMap()
        )
    )

    private val gameDbEntity = GameDbEntity(
        uuid = GAME_UUID,
        settings = GameSettingsDbEntity(
            players = GAME_PLAYERS
        ),
        state = GameStateDbEntity(
            gamePhase = GamePhases.PREPARING,
            playingPlayer = "",
            playerOrder = emptyMap(),
            endGameOrder = emptyMap()
        )
    )

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)

        coEvery { gameNetworkSource.createGame(any()) } returns gameNetEntity

        coEvery { gameDao.insert(any()) } returns 0
        coEvery { gameDao.get(any()) } returns gameDbEntity

        gameRepository = ProdGameRepository(
            gameDao = gameDao,
            gameNetworkSource = gameNetworkSource
        )
    }

    @Test
    fun `createGame() calls network and database sources`() = runTest {
        val uuid = gameRepository.createGame(gameSettings)

        uuid shouldNotBe ""
        uuid shouldNotBe null

        coVerify(exactly = 1) { gameNetworkSource.createGame(any()) }
        coVerify(exactly = 1) { gameDao.insert(any()) }

        coVerifyAll { gameNetworkSource.createGame(any()) }
        coVerifyAll { gameDao.insert(any()) }

        coVerifyOrder {
            gameNetworkSource.createGame(any())
            gameDao.insert(any())
        }
    }

    @Test
    fun `saveGame() calls only database`() = runTest {
        gameRepository.saveGame(game)

        coVerify(exactly = 1) { gameDao.insert(any()) }
        coVerify { gameNetworkSource wasNot called }

        coVerifyAll { gameDao.insert(any()) }
    }

    @Test
    fun `getGame() calls only database`() = runTest {
        val game = gameRepository.getGame(GAME_UUID)

        game.uuid shouldBe GAME_UUID

        coVerify(exactly = 1) { gameDao.get(GAME_UUID) }
        coVerify { gameNetworkSource wasNot called }

        coVerifyAll { gameDao.get(GAME_UUID) }
    }
}
