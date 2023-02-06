package cz.johnyapps.lora

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import cz.johnyapps.lora.feature.core.LoraTheme
import cz.johnyapps.lora.feature.firebasesignin.GoogleAccountManager
import cz.johnyapps.lora.feature.firebasesignin.ProdGoogleAccountManager
import cz.johnyapps.lora.feature.firebasesignin.SignedIn
import cz.johnyapps.lora.feature.joingame.navigation.JOIN_GAME_ROUTE
import cz.johnyapps.lora.navigation.LoraNavHost
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val googleAccountManager: GoogleAccountManager by lazy {
        ProdGoogleAccountManager(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        observeGoogleSignInState()

        setContent {
            LoraTheme(
                darkTheme = false
            ) {
                Contents()
            }
        }

        googleAccountManager.checkIfUserIsSignedIn()
    }

    private fun observeGoogleSignInState() {
        lifecycleScope.launch {
            googleAccountManager.state.collect { signedIn ->
                Log.d(TAG, "observeGoogleSignInState: ${signedIn.name}")

                if (signedIn is SignedIn.No) {
                    googleAccountManager.triggerSignIn()
                }
            }
        }

        lifecycleScope.launch {
            googleAccountManager.errors.collect { error ->
                Log.w(TAG, "observeGoogleSignInState: Error: ${error.localizedMessage}", error)
            }
        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Contents() {
    val navHostController = rememberNavController()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
    ) { paddingValues ->
        LoraNavHost(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            navController = navHostController,
            startDestination = JOIN_GAME_ROUTE
        )
    }
}
