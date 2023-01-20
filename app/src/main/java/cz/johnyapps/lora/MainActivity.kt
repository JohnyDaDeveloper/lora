package cz.johnyapps.lora

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cz.johnyapps.lora.feature.card.Card
import cz.johnyapps.lora.feature.card.PlayingCard
import cz.johnyapps.lora.feature.card.Symbol
import cz.johnyapps.lora.feature.card.Value
import cz.johnyapps.lora.feature.core.LoraTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            LoraTheme(
                darkTheme = false
            ) {
                Contents()
            }
        }
    }
}

@Composable
private fun Contents() {
    PlayingCard(
        width = 500.dp,
        borderWidth = 10.dp,
        card = Card(
            Symbol.Hearths,
            Value.Seven
        )
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ContentsPreview() {
    LoraTheme {
        Contents()
    }
}