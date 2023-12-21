package com.lucasnvs.cadmo

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.lucasnvs.cadmo.ui.screens.Screen

@Stable
class CadmoAppState(
    val navController: NavHostController,
) {
    val currentDestination: NavDestination?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination


    fun navigate(destination: Screen) {
        navController.navigate(destination.route) {
            popUpTo(Screen.HomeScreen.route)
        }
    }

    fun popBackStack(route: String, inclusive: Boolean, saveState: Boolean) {
        navController.popBackStack(route = route, inclusive = inclusive, saveState = saveState)
    }

    fun popBackStack() {
        navController.popBackStack()
    }
}