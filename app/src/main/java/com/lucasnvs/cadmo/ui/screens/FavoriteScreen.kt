package com.lucasnvs.cadmo.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.lucasnvs.cadmo.CadmoAppState
import com.lucasnvs.cadmo.Navigation
import com.lucasnvs.cadmo.ui.components.bars.MainTopBar
import com.lucasnvs.cadmo.ui.components.bars.NavigationBottomBar
import com.lucasnvs.cadmo.ui.theme.CadmoTheme

@Composable
fun FavoriteScreen(
    modifier: Modifier = Modifier,
    appState: CadmoAppState
) {
    Scaffold(
        topBar = {
            MainTopBar(
                title = { Text(text = "Favoritos", fontSize = 19.sp) },
                actionIcon = {
                    Icon(
                        imageVector = Icons.Filled.ShoppingCart,
                        contentDescription = "Carrinho",
                        modifier = modifier.size(35.dp)
                    )
                },
                actionOnClick = {}
            )
        },
        bottomBar = {
            NavigationBottomBar(
                currentDestination = appState.currentDestination,
                onNavigateToHome = { appState.popBackStack(route = Screen.HomeScreen.route, inclusive = false, saveState = false) },
                onNavigateToDepartament = {appState.navigate(Screen.DepartamentScreen)},
                onNavigateToProfile = {appState.navigate(Screen.ProfileScreen)}
            )
        }
    ) { innerPadding ->
        Column(modifier.padding(innerPadding)) {
            Text("Favoritos")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FavoriteScreenPreview() {
    CadmoTheme {
        FavoriteScreen(appState = CadmoAppState(rememberNavController()))
    }
}