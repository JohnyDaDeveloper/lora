package cz.johnyapps.lora.feature.creategame.usecase

import cz.johnyapps.lora.core.data.game.GameRepository
import io.kotest.matchers.shouldNotBe
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.coVerifyAll
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

private const val GAME_UUID = "SomeCoolUUID123"
private const val PLAYER_COUNT = 5

internal class ProdCreateGameUseCaseTest {
    @MockK
    lateinit var gameRepository: GameRepository

    private lateinit var createGameUseCase: CreateGameUseCase

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)

        coEvery { gameRepository.createGame(any()) } returns GAME_UUID

        createGameUseCase = ProdCreateGameUseCase(
            gameRepository = gameRepository
        )
    }

    @Test
    fun `invoke() calls GameRepository`() = runTest {
        val uuid = createGameUseCase.invoke(PLAYER_COUNT)

        uuid shouldNotBe ""
        uuid shouldNotBe null

        coVerify(exactly = 1) { gameRepository.createGame(any()) }

        coVerifyAll { gameRepository.createGame(any()) }
    }
}
