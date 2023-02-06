package cz.johnyapps.lora.feature.firebasesignin

import android.app.Activity
import android.content.Intent
import android.content.IntentSender
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import cz.johnyapps.lora.core.constants.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProdGoogleAccountManager(
    private val activity: ComponentActivity
) : GoogleAccountManager {
    private val coroutineScope: CoroutineScope get() = activity.lifecycleScope

    private val oneTapClient: SignInClient by lazy { Identity.getSignInClient(activity) }
    private val firebaseAuth: FirebaseAuth by lazy {
        FirebaseApp.initializeApp(activity)
        Firebase.auth
    }

    private val _signedIn = MutableStateFlow<SignedIn>(SignedIn.Loading)
    override val state: StateFlow<SignedIn> = _signedIn

    private val _errors = MutableSharedFlow<Exception>()
    override val errors: Flow<Exception> = _errors

    private val resultLauncher = activity.registerForActivityResult(
        ActivityResultContracts.StartIntentSenderForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            handleSingInResult(result.data)
        }
    }

    override fun checkIfUserIsSignedIn() {
        Log.d(TAG, "checkIfUserIsSignedIn: Checking if user is signed in...")
        val user = firebaseAuth.currentUser

        if (user != null) {
            handleFirebaseUser(user)
        } else {
            Log.i(TAG, "checkIfUserIsSignedIn: User is not signed in")
            coroutineScope.launch { _signedIn.emit(SignedIn.No) }
        }
    }

    override fun triggerSignIn() {
        val signInRequest = BeginSignInRequest.builder()
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    .setFilterByAuthorizedAccounts(false)
                    .setServerClientId(Constants.GOOGLE_CLIENT_ID)
                    .build()
            ).setAutoSelectEnabled(true)
            .build()

        oneTapClient.beginSignIn(signInRequest)
            .addOnSuccessListener(activity) { result ->
                try {
                    val intentSender = result.pendingIntent.intentSender
                    val request = IntentSenderRequest.Builder(intentSender).build()
                    resultLauncher.launch(request)
                } catch (e: IntentSender.SendIntentException) {
                    coroutineScope.launch { _errors.emit(e) }
                }
            }.addOnFailureListener { e ->
                coroutineScope.launch { _errors.emit(e) }
            }
    }

    private fun handleFirebaseUser(firebaseUser: FirebaseUser) {
        Log.i(TAG, "handleFirebaseUser: Signed in as ${firebaseUser.providerData.lastOrNull()?.email}")
        coroutineScope.launch { _signedIn.emit(SignedIn.Yes) }
    }

    private fun handleSingInResult(data: Intent?) {
        try {
            val credential = oneTapClient.getSignInCredentialFromIntent(data)
            val idToken = credential.googleIdToken

            if (!idToken.isNullOrEmpty()) {
                Log.d(TAG, "handleSingInResult: Got ID token")
                handleGoogleIdToken(idToken)
            }
        } catch (e: Exception) {
            coroutineScope.launch { _errors.emit(e) }
        }
    }

    private fun handleGoogleIdToken(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener(activity) { task ->
                val result = task.result

                if (task.isSuccessful) {
                    Log.d(TAG, "handleGoogleIdToken: Success")
                    val user = result.user

                    if (user != null) {
                        handleFirebaseUser(user)
                    }
                } else {
                    val error = task.exception

                    if (error != null) {
                        coroutineScope.launch { _errors.emit(error) }
                    }
                }
            }
    }

    companion object {
        private const val TAG = "GoogleAccountManager"
    }
}
