package cz.johnyapps.lora.feature.creategame

private const val THREE = 3
private const val FOUR = 3
private const val FIVE = 3

sealed class PlayerCount {
    abstract val value: Int
    abstract val next: PlayerCount

    object Three : PlayerCount() {
        override val value: Int = THREE
        override val next: PlayerCount = Four
    }

    object Four : PlayerCount() {
        override val value: Int = FOUR
        override val next: PlayerCount = Five
    }

    object Five : PlayerCount() {
        override val value: Int = FIVE
        override val next: PlayerCount = Three
    }
}
