package cz.johnyapps.lora.feature.creategame.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import cz.johnyapps.lora.feature.creategame.CreateGameRoute

const val createGameRoute = "create_game_route"

fun NavController.navigateToCreateGame(navOptions: NavOptions? = null) {
    this.navigate(createGameRoute, navOptions)
}

fun NavGraphBuilder.createGameScreen() {
    composable(route = createGameRoute) {
        CreateGameRoute()
    }
}