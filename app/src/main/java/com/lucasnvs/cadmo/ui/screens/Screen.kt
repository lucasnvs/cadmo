package com.lucasnvs.cadmo.ui.screens

sealed class Screen(val route: String) {
    object HomeScreen: Screen("home")
    object DepartamentScreen: Screen("departament")
    object ProfileScreen: Screen("profile")
}
