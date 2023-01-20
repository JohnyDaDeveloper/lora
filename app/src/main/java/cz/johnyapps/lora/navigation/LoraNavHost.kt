package cz.johnyapps.lora.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import cz.johnyapps.lora.feature.joingame.navigation.joinGameScreen

@Composable
fun LoraNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    onBackClick: () -> Unit,
    startDestination: String
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        joinGameScreen(
            navigateToCreateGame = {
                // TODO
            }
        )
    }
}