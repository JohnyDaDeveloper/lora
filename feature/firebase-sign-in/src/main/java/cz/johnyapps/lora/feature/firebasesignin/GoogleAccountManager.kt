package cz.johnyapps.lora.feature.firebasesignin

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface GoogleAccountManager {
    val state: StateFlow<SignedIn>
    val errors: Flow<Exception>

    fun checkIfUserIsSignedIn()

    fun triggerSignIn()
}
