package cz.johnyapps.lora.feature.creategame

import cz.johnyapps.lora.feature.creategame.usecase.CreateGameUseCase
import io.kotest.matchers.shouldBe
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.coVerifyAll
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

private const val GAME_UUID = "SomeCoolUUID123"

internal class CreateGameViewModelTest {
    @MockK
    lateinit var createGameUseCase: CreateGameUseCase

    private lateinit var createGameViewModel: CreateGameViewModel

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)

        Dispatchers.setMain(StandardTestDispatcher())

        coEvery { createGameUseCase.invoke(any()) } returns GAME_UUID

        createGameViewModel = CreateGameViewModel(
            createGameUseCase = createGameUseCase
        )
    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `Init UI state should have player count Four and showWorking false`() = runTest {
        val uiState = createGameViewModel.uiState.value

        uiState.playerCount shouldBe PlayerCount.Four
        uiState.showWorking shouldBe false
    }

    @Test
    fun `onNextPlayerCount() results in rotating player count and working is always false`() =
        runTest {
            val collectJob = launch(UnconfinedTestDispatcher(testScheduler)) {
                createGameViewModel.uiState.collect()
            }

            createGameViewModel.onNextPlayerCount()

            advanceUntilIdle()

            val firstUiState = createGameViewModel.uiState.value

            firstUiState.playerCount shouldBe PlayerCount.Five
            firstUiState.showWorking shouldBe false

            createGameViewModel.onNextPlayerCount()

            advanceUntilIdle()

            val secondUiState = createGameViewModel.uiState.value

            secondUiState.playerCount shouldBe PlayerCount.Three
            secondUiState.showWorking shouldBe false

            createGameViewModel.onNextPlayerCount()

            advanceUntilIdle()

            val thirdUiState = createGameViewModel.uiState.value

            thirdUiState.playerCount shouldBe PlayerCount.Four
            thirdUiState.showWorking shouldBe false

            collectJob.cancel()
        }

    @Test
    fun `onCreate() leaves count unchanged and sets working to true`() = runTest {
        val collectJob = launch(UnconfinedTestDispatcher(testScheduler)) {
            createGameViewModel.uiState.collect()
        }

        createGameViewModel.onCreate()

        advanceUntilIdle()

        val uiState = createGameViewModel.uiState.value

        uiState.playerCount shouldBe PlayerCount.Four
        uiState.showWorking shouldBe true

        collectJob.cancel()

        coVerify(exactly = 1) { createGameUseCase.invoke(any()) }

        coVerifyAll { createGameUseCase.invoke(any()) }
    }
}
