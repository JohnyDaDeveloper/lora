package cz.johnyapps.lora.feature.joingame

import android.util.Log
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
class JoinGameViewModel @Inject constructor() : ViewModel() {
    private val roomIdFlow = MutableStateFlow("")

    val uiState: StateFlow<JoinGameUiState> = roomIdFlow.map { id ->
        JoinGameUiState(roomId = id)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(UI_STATE_TIMEOUT),
        initialValue = JoinGameUiState(roomId = "")
    )

    fun onRoomIdChange(roomId: String) {
        roomIdFlow.value = roomId
    }

    fun onJoin() {
        Log.d(TAG, "onJoin: Joining...")
    }

    companion object {
        private const val TAG = "JoinGameViewModel"
        private const val UI_STATE_TIMEOUT = 5_000L
    }
}
