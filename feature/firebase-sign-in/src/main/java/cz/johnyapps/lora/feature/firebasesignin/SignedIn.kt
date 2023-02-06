package cz.johnyapps.lora.feature.firebasesignin

sealed class SignedIn {
    val name: String get() = javaClass.simpleName

    object Loading : SignedIn()

    object Yes : SignedIn()

    object No : SignedIn()
}
