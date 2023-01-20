package cz.johnyapps.lora.card

import androidx.annotation.StringRes
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

private const val ratio = 1.4

@Composable
fun PlayingCard(
    width: Dp,
    borderWidth: Dp,
    card: Card
) {
    val height = width.value * ratio
    val symbolSize = width.value / 4

    Card(
        modifier = Modifier
            .size(
                width,
                height.dp
            )
            .border(
                borderWidth,
                MaterialTheme.colorScheme.onSurface,
                MaterialTheme.shapes.medium
            )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            SymbolIcon(
                modifier = Modifier
                    .padding(borderWidth)
                    .size(symbolSize.dp)
                    .align(Alignment.TopStart),
                symbol = card.symbol,
                contentDescription = stringResource(
                    id = symbolToStringId(card.symbol)
                )
            )

            Text(
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.Center),
                text = valueToString(card.value),
                style = MaterialTheme.typography.headlineLarge
            )

            SymbolIcon(
                modifier = Modifier
                    .padding(borderWidth)
                    .size(symbolSize.dp)
                    .align(Alignment.BottomEnd),
                symbol = card.symbol,
                contentDescription = stringResource(
                    id = symbolToStringId(card.symbol)
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PlayingCardPreview() {
    MaterialTheme {
        PlayingCard(
            width = 100.dp,
            borderWidth = 4.dp,
            card = Card(
                Symbol.Hearths,
                Value.Seven
            )
        )
    }
}

private fun valueToString(value: Value): String {
    return when (value) {
        is Value.Seven -> {
            "7"
        }

        is Value.Eight -> {
            "8"
        }

        is Value.Nine -> {
            "9"
        }

        is Value.Ten -> {
            "10"
        }

        is Value.UnderKnave -> {
            "UK"
        }

        is Value.OverKnave -> {
            "OK"
        }

        is Value.King -> {
            "K"
        }

        is Value.Ace -> {
            "A"
        }
    }
}

@StringRes
private fun symbolToStringId(symbol: Symbol): Int {
    return when (symbol) {
        is Symbol.Acrons -> {
            R.string.symbol_name_acrons
        }

        is Symbol.Bells -> {
            R.string.symbol_name_bells
        }

        is Symbol.Hearths -> {
            R.string.symbol_name_hearths
        }

        is Symbol.Leaves -> {
            R.string.symbol_name_leaves
        }
    }
}
