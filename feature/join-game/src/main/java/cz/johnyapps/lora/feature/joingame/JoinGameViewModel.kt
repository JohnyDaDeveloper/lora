package cz.johnyapps.lora.feature.joingame

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class JoinGameViewModel : ViewModel() {
    private val roomIdFlow = MutableStateFlow("")

    val uiState: StateFlow<JoinGameUiState> = roomIdFlow.map { id ->
        JoinGameUiState(roomId = id)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = JoinGameUiState(roomId = "")
    )

    fun onRoomIdChange(roomId: String) {
        roomIdFlow.value = roomId
    }

    fun onJoin() {

    }
}