package com.lucasnvs.cadmo

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lucasnvs.cadmo.ui.screens.DepartamentScreen
import com.lucasnvs.cadmo.ui.screens.FavoriteScreen
import com.lucasnvs.cadmo.ui.screens.HomeScreen
import com.lucasnvs.cadmo.ui.screens.ProfileScreen
import com.lucasnvs.cadmo.ui.screens.Screen

@Composable
fun Navigation() {
    val navController: NavHostController = rememberNavController()
    val appState = CadmoAppState( navController = navController )

    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route
    ) {
        composable(Screen.HomeScreen.route) {
            HomeScreen( appState = appState )
        }

        composable(Screen.DepartamentScreen.route) {
            DepartamentScreen ( appState = appState )
        }

        composable(Screen.FavoriteScreen.route) {
            FavoriteScreen ( appState = appState )
        }

        composable(Screen.ProfileScreen.route) {
            ProfileScreen( appState = appState )
        }
    }
}