package cz.johnyapps.lora.feature.creategame

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import cz.johnyapps.lora.feature.core.LoraTheme

@Composable
internal fun CreateGameRoute(
    modifier: Modifier = Modifier,
    viewModel: CreateGameViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    CreateGameScreen(
        modifier = modifier,
        state = uiState,
        onNextPlayerCount = viewModel::onNextPlayerCount,
        onCreate = viewModel::onCreate
    )

    BackHandler(uiState.showWorking) {
        // Consume onBack event
    }
}

@Composable
internal fun CreateGameScreen(
    modifier: Modifier = Modifier,
    state: CreateGameUiState,
    onNextPlayerCount: () -> Unit,
    onCreate: () -> Unit
) {
    if (state.showWorking) {
        Working(
            modifier = Modifier
                .fillMaxSize()
                .zIndex(1f)
        )
    }

    Content(
        modifier = modifier.zIndex(0f),
        state = state,
        onNextPlayerCount = onNextPlayerCount,
        onCreate = onCreate
    )
}

@Composable
private fun Working(
    modifier: Modifier = Modifier,
) {
    val interactionSource = remember { MutableInteractionSource() }

    Row(
        modifier = modifier
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) { }
            .padding(32.dp)
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.75f)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(48.dp)
                .padding(end = 8.dp)
        )

        Text(
            modifier = Modifier
                .wrapContentSize(),
            text = stringResource(id = R.string.text_working),
            style = LoraTheme.typography.bodyMedium
        )
    }
}

@Composable
private fun Content(
    modifier: Modifier = Modifier,
    state: CreateGameUiState,
    onNextPlayerCount: () -> Unit,
    onCreate: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(bottom = 8.dp),
            text = stringResource(id = R.string.text_player_count),
            textAlign = TextAlign.Center,
            style = LoraTheme.typography.bodyMedium
        )

        TextButton(onClick = onNextPlayerCount) {
            Text(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .wrapContentHeight(),
                text = state.playerCount.value.toString(),
                style = LoraTheme.typography.headlineLarge,
                textAlign = TextAlign.Center
            )
        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(top = 8.dp),
            onClick = onCreate,
            shape = LoraTheme.shapes.small
        ) {
            Text(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(8.dp),
                text = stringResource(id = R.string.button_create_text),
                style = LoraTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
            )
        }
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
private fun CreateGameScreenPreview() {
    var state by remember {
        mutableStateOf(
            CreateGameUiState(
                playerCount = PlayerCount.Four
            )
        )
    }

    LoraTheme {
        CreateGameScreen(
            state = state,
            onNextPlayerCount = {
                state = CreateGameUiState(
                    playerCount = state.playerCount.next
                )
            },
            onCreate = {}
        )
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
private fun CreateGameScreenWorkingPreview() {
    var state by remember {
        mutableStateOf(
            CreateGameUiState(
                playerCount = PlayerCount.Four,
                showWorking = true
            )
        )
    }

    LoraTheme {
        CreateGameScreen(
            state = state,
            onNextPlayerCount = {
                state = CreateGameUiState(
                    playerCount = state.playerCount.next
                )
            },
            onCreate = {}
        )
    }
}
