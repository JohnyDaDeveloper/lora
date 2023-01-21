package cz.johnyapps.lora.feature.creategame

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class CreateGameViewModel @Inject constructor(

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
    }
}