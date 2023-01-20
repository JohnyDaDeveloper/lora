package cz.johnyapps.lora.feature.joingame

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import cz.johnyapps.lora.feature.core.LoraTheme

@Composable
internal fun JoinGameRoute(
    modifier: Modifier = Modifier,
    viewModel: JoinGameViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    JoinGameScreen(
        modifier = modifier,
        state = uiState,
        onRoomIdChange = viewModel::onRoomIdChange,
        onJoin = viewModel::onJoin
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun JoinGameScreen(
    modifier: Modifier = Modifier,
    state: JoinGameUiState,
    onRoomIdChange: (roomId: String) -> Unit,
    onJoin: () -> Unit
) {
    Column(
        modifier = modifier
            .padding(32.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(bottom = 16.dp),
            value = state.roomId,
            onValueChange = { onRoomIdChange(it) },
            textStyle = LoraTheme.typography.bodyMedium,
            singleLine = true,
            label = {
                Text(
                    text = stringResource(id = R.string.input_room_id_label),
                    style = LoraTheme.typography.bodySmall
                )
            }
        )

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            onClick = onJoin,
            shape = LoraTheme.shapes.small
        ) {
            Text(
                text = stringResource(id = R.string.button_join_text),
                style = LoraTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
            )
        }
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
private fun JoinGameScreenPreview() {
    LoraTheme {
        JoinGameScreen(
            state = JoinGameUiState(
                roomId = "CoolRoomId"
            ),
            onRoomIdChange = {},
            onJoin = {}
        )
    }
}