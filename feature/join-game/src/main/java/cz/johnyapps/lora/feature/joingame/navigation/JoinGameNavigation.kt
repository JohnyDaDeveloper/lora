package cz.johnyapps.lora.feature.joingame.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import cz.johnyapps.lora.feature.joingame.JoinGameRoute

const val joinGameRoute = "join_game_route"

fun NavController.navigateToJoinGame(navOptions: NavOptions? = null) {
    this.navigate(joinGameRoute, navOptions)
}

fun NavGraphBuilder.joinGameScreen(
    navigateToCreateGame: () -> Unit
) {
    composable(route = joinGameRoute) {
        JoinGameRoute(
            navigateToCreateGame = navigateToCreateGame
        )
    }
}