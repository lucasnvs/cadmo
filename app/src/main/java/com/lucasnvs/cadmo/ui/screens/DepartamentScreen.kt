package com.lucasnvs.cadmo.ui.screens

import android.graphics.drawable.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.lucasnvs.cadmo.CadmoAppState
import com.lucasnvs.cadmo.ui.components.ListRedirectOption
import com.lucasnvs.cadmo.ui.components.SearchTextField
import com.lucasnvs.cadmo.ui.components.bars.NavigationBottomBar
import com.lucasnvs.cadmo.ui.components.bars.MainTopBar
import com.lucasnvs.cadmo.ui.theme.CadmoTheme

val testlist: List<Pair<String, ImageVector?>> = listOf(
    "Hardware" to null,
    "Periféricos" to null,
    "Mobile" to null,
    "Casa Inteligente" to null,
    "Games" to null,
    "Automação" to null,
    "Segurança" to null,
    "Áudio" to null,
    "Decoração" to null,
    "Acessórios" to null,
)
@Composable
fun DepartamentScreen(
    modifier: Modifier = Modifier,
    appState: CadmoAppState,
) {
    Scaffold(
        topBar = {
            MainTopBar(
                title = { SearchTextField(modifier = modifier.fillMaxWidth(0.95F)) },
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
                onNavigateToFavorite = {appState.navigate(Screen.FavoriteScreen)},
                onNavigateToProfile = {appState.navigate(Screen.ProfileScreen)}
            )
        },
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(Color(0xFFF9F9F9))
                .padding(innerPadding)
                .padding(15.dp)
        ) {
            Text(text = "DEPARTAMENTOS", fontSize = 21.sp)
            Spacer(modifier = modifier.height(10.dp))
            ListRedirectOption(items = testlist)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DepartamentScreenPreview() {
    DepartamentScreen(appState = CadmoAppState( navController = rememberNavController() ))
}