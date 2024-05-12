package com.lucasnvs.cadmo.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lucasnvs.cadmo.ui.CadmoAppState
import com.lucasnvs.cadmo.ui.departament.DepartamentScreen
import com.lucasnvs.cadmo.ui.favorite.FavoriteScreen
import com.lucasnvs.cadmo.ui.home.HomeScreen
import com.lucasnvs.cadmo.ui.profile.ProfileScreen
import com.lucasnvs.cadmo.ui.shared.Screen

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