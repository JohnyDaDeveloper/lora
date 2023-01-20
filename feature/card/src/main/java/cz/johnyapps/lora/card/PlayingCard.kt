package cz.johnyapps.lora.card

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

private const val ratio = 1.4

@Composable
fun PlayingCard(
    width: Int
) {
    val height = width * ratio

    Card(
        modifier = Modifier.size(
            width.dp,
            height.dp
        )
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Seven of hearths",
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview
@Composable
private fun PlayingCardPreview() {
    MaterialTheme {
        PlayingCard(width = 100)
    }
}
