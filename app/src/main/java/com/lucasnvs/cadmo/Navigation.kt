package com.lucasnvs.cadmo

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lucasnvs.cadmo.ui.screens.DepartamentScreen
import com.lucasnvs.cadmo.ui.screens.HomeScreen
import com.lucasnvs.cadmo.ui.screens.ProfileScreen
import com.lucasnvs.cadmo.ui.screens.Screen

@Composable
fun Navigation() {
    val navController: NavHostController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route
    ) {
        composable(Screen.HomeScreen.route) {
            HomeScreen( navController = navController )
        }

        composable(Screen.DepartamentScreen.route) {
            DepartamentScreen ( navController = navController)
        }

        composable(Screen.ProfileScreen.route) {
            ProfileScreen( navController = navController )
        }
    }
}