package cz.johnyapps.lora.feature.joingame.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import cz.johnyapps.lora.feature.joingame.JoinGameRoute

const val JOIN_GAME_ROUTE = "join_game_route"

fun NavController.navigateToJoinGame(navOptions: NavOptions? = null) {
    this.navigate(JOIN_GAME_ROUTE, navOptions)
}

fun NavGraphBuilder.joinGameScreen(
    navigateToCreateGame: () -> Unit
) {
    composable(route = JOIN_GAME_ROUTE) {
        JoinGameRoute(
            navigateToCreateGame = navigateToCreateGame
        )
    }
}
