package com.lucasnvs.cadmo.ui.favorite

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.lucasnvs.cadmo.ui.CadmoAppState
import com.lucasnvs.cadmo.ui.components.Product
import com.lucasnvs.cadmo.ui.components.bars.MainTopBar
import com.lucasnvs.cadmo.ui.components.bars.NavigationBottomBar
import com.lucasnvs.cadmo.ui.home.Loading
import com.lucasnvs.cadmo.ui.shared.Screen
import com.lucasnvs.cadmo.ui.theme.CadmoTheme

@Composable
fun FavoriteScreen(
    modifier: Modifier = Modifier,
    appState: CadmoAppState,
    viewModel: FavoriteViewModel = hiltViewModel()
) {

    val favoriteProducts = viewModel.uiState.favoriteProducts

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
        if(viewModel.uiState.isLoading) {
            Loading(modifier = Modifier, innerPadding = innerPadding)
        } else {
            Box(
                modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .background(Color(0xFFF9F9F9))
            ) {

                LazyColumn(
                    modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(
                        space = 10.dp,
                        alignment = Alignment.CenterVertically
                    ),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(favoriteProducts) {  product ->
                        Product(
                            modifier = Modifier,
                            product = product,
                            onFavoriteButtonClick = { viewModel.onItemFavoriteClick(favoriteProducts.indexOf(product))}
                        )
                    }
                }
            }
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