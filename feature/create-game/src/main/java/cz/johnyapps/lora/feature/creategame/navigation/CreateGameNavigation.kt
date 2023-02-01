package cz.johnyapps.lora.feature.creategame.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import cz.johnyapps.lora.feature.creategame.CreateGameRoute

const val CREATE_GAME_ROUTE = "create_game_route"

fun NavController.navigateToCreateGame(navOptions: NavOptions? = null) {
    this.navigate(CREATE_GAME_ROUTE, navOptions)
}

fun NavGraphBuilder.createGameScreen() {
    composable(route = CREATE_GAME_ROUTE) {
        CreateGameRoute()
    }
}
