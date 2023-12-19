package com.lucasnvs.cadmo.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.lucasnvs.cadmo.ui.components.CadmoBottomAppBar
import com.lucasnvs.cadmo.ui.components.CadmoSimpleTopAppBar

@Composable
fun DepartamentScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    Scaffold(
        topBar = {
            CadmoSimpleTopAppBar(title = "Departamentos", actionIcon = Icons.Filled.ShoppingCart, actionOnClick = {})
        },
        bottomBar = {
            CadmoBottomAppBar(
                onNavigateToHome = { navController.popBackStack(route = Screen.HomeScreen.route, inclusive = false, saveState = false) },
                onNavigateToDepartament = {},
                onNavigateToProfile = {navController.navigate(Screen.ProfileScreen.route) { popUpTo(Screen.HomeScreen.route)} }
            )
        },
    ) { innerPadding ->
        Box(modifier = modifier.padding(innerPadding)) {
            Text("Departamentos")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DepartamentScreenPreview() {
    DepartamentScreen(navController = rememberNavController())
}