package com.lucasnvs.cadmo.ui.screens

import androidx.compose.foundation.layout.Box
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
import com.lucasnvs.cadmo.CadmoAppState
import com.lucasnvs.cadmo.ui.components.bars.NavigationBottomBar
import com.lucasnvs.cadmo.ui.components.bars.MainTopBar

@Composable
fun DepartamentScreen(
    modifier: Modifier = Modifier,
    appState: CadmoAppState,
) {
    Scaffold(
        topBar = {
            MainTopBar(title = { Text(text = "Departamentos", fontSize = 19.sp)},
                actionIcon = { Icon(
                    imageVector = Icons.Filled.ShoppingCart,
                    contentDescription = "Carrinho",
                    modifier = modifier.size(35.dp)
                ) },
                actionOnClick = {}
            )
        },
        bottomBar = {
            NavigationBottomBar(
                currentDestination = appState.currentDestination,
                onNavigateToHome = { appState.popBackStack(route = Screen.HomeScreen.route, inclusive = false, saveState = false) },
                onNavigateToDepartament = {},
                onNavigateToProfile = {appState.navigate(Screen.ProfileScreen)}
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
//    DepartamentScreen(navController = rememberNavController())
}