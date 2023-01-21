package cz.johnyapps.lora.feature.creategame

sealed class PlayerCount {
    abstract val value: Int
    abstract val next: PlayerCount

    object Three : PlayerCount() {
        override val value: Int = 3
        override val next: PlayerCount = Four
    }

    object Four : PlayerCount() {
        override val value: Int = 4
        override val next: PlayerCount = Five
    }

    object Five : PlayerCount() {
        override val value: Int = 5
        override val next: PlayerCount = Three
    }
}