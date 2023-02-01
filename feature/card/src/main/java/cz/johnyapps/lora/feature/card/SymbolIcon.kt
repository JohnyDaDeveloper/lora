package cz.johnyapps.lora.feature.card

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cz.johnyapps.lora.feature.core.LoraTheme

@Composable
fun SymbolIcon(
    modifier: Modifier = Modifier,
    symbol: Symbol,
    contentDescription: String
) {
    Icon(
        modifier = modifier,
        painter = painterResource(id = symbol.id),
        contentDescription = contentDescription,
        tint = Color.Unspecified, // Prevents tint overriding drawable color
    )
}

@Preview(showBackground = true)
@Composable
private fun SymbolIconPreview() {
    LoraTheme {
        SymbolIcon(
            modifier = Modifier.size(100.dp),
            symbol = Symbol.Acrons,
            contentDescription = "Acrons"
        )
    }
}
