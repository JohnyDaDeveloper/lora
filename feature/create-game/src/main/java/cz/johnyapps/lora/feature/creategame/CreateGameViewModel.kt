package cz.johnyapps.lora.feature.creategame

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class CreateGameViewModel @Inject constructor(

) : ViewModel() {
    private val playerCount = MutableStateFlow<PlayerCount>(PlayerCount.Four)

    val uiState: StateFlow<CreateGameUiState> = playerCount
        .map { count ->
            CreateGameUiState(
                playerCount = count
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = CreateGameUiState(
                playerCount = PlayerCount.Four
            )
        )

    fun onNextPlayerCount() {
        playerCount.value = playerCount.value.next
    }

    fun onCreate() {
        // TODO
    }
}