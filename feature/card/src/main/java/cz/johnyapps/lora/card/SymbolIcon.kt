package cz.johnyapps.lora.card

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SymbolIcon(
    modifier: Modifier,
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
    MaterialTheme {
        SymbolIcon(
            modifier = Modifier.size(100.dp),
            symbol = Symbol.Acrons,
            contentDescription = "Acrons"
        )
    }
}