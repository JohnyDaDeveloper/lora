package cz.johnyapps.lora

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import cz.johnyapps.lora.feature.core.LoraTheme
import cz.johnyapps.lora.feature.joingame.navigation.joinGameRoute
import cz.johnyapps.lora.navigation.LoraNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            LoraTheme(
                darkTheme = false
            ) {
                Contents()
            }
        }
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
            onBackClick = { navHostController.navigateUp() },
            startDestination = joinGameRoute
        )
    }
}