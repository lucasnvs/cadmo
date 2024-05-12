package com.lucasnvs.cadmo.ui.profile

sealed class UnsignedScreen(val route: String) {
    object LoginScreen: UnsignedScreen("login")
    object SignIn: UnsignedScreen("signin")
}