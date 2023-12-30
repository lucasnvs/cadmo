package com.lucasnvs.cadmo.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.lucasnvs.cadmo.CadmoAppState
import com.lucasnvs.cadmo.ui.components.bars.NavigationBottomBar
import com.lucasnvs.cadmo.ui.components.bars.MainTopBar

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    appState: CadmoAppState,
) {
    Scaffold(
        topBar = {
            MainTopBar(title = { Text(text = "Meu Perfil", fontSize = 19.sp) })
        },
        bottomBar = {
            NavigationBottomBar(
                currentDestination = appState.currentDestination,
                onNavigateToHome = { appState.popBackStack() },
                onNavigateToDepartament = { appState.navigate(Screen.DepartamentScreen) },
                onNavigateToFavorite = { appState.navigate(Screen.FavoriteScreen) },
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