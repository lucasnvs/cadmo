package com.lucasnvs.cadmo.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.lucasnvs.cadmo.CadmoAppState
import com.lucasnvs.cadmo.ui.components.CadmoBottomAppBar
import com.lucasnvs.cadmo.ui.components.CadmoSimpleTopAppBar

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    appState: CadmoAppState,
) {
    Scaffold(
        topBar = {
            CadmoSimpleTopAppBar(title = "Meu Perfil")
        },
        bottomBar = {
            CadmoBottomAppBar(
                currentDestination = appState.currentDestination,
                onNavigateToHome = { appState.popBackStack() },
                onNavigateToDepartament = { appState.navigate(Screen.DepartamentScreen) },
                onNavigateToProfile = {}
            )
        },
        ) { innerPadding ->
        Box(modifier = modifier.padding(innerPadding)) {
            Text(text = "Olá você está no perfil")
        }
    }

}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
//    ProfileScreen(navController = rememberNavController())
}