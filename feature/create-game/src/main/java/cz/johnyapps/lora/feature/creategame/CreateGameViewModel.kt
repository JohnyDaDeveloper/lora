package cz.johnyapps.lora.feature.creategame

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.johnyapps.lora.feature.creategame.usecase.CreateGameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateGameViewModel @Inject constructor(
    private val createGameUseCase: CreateGameUseCase
) : ViewModel() {
    private val playerCount = MutableStateFlow<PlayerCount>(PlayerCount.Four)
    private val working = MutableStateFlow(false)

    val uiState: StateFlow<CreateGameUiState> = combine(
        playerCount,
        working
    ) { count, working ->
        CreateGameUiState(
            playerCount = count,
            showWorking = working
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = CreateGameUiState()
    )

    fun onNextPlayerCount() {
        playerCount.value = playerCount.value.next
    }

    fun onCreate() {
        working.value = true

        viewModelScope.launch {
            val players = playerCount.value.value

            Log.d(TAG, "onCreate: Creating game for $players players")

            createGameUseCase(players)

            Log.d(TAG, "onCreate: Game created!")
        }
    }

    companion object {
        private const val TAG = "CreateGameViewModel"
    }
}