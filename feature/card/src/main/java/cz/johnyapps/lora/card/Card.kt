package cz.johnyapps.lora.card

import androidx.annotation.DrawableRes

data class Card(
    val symbol: Symbol,
    val value: Value
)

sealed class Symbol {
    @get:DrawableRes
    abstract val id: Int

    object Acrons : Symbol() {
        override val id: Int = R.drawable.symbol_acorns
    }

    object Bells : Symbol() {
        override val id: Int = R.drawable.symbol_bells
    }

    object Hearths : Symbol() {
        override val id: Int = R.drawable.symbol_hearts
    }

    object Leaves : Symbol() {
        override val id: Int = R.drawable.symbol_leaves
    }
}

sealed class Value {
    object Seven : Value()

    object Eight : Value()

    object Nine : Value()

    object Ten : Value()

    object UnderKnave : Value()

    object OverKnave : Value()

    object King : Value()

    object Ace : Value()
}