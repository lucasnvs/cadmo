package com.lucasnvs.cadmo.ui.favorite

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.lucasnvs.cadmo.R
import com.lucasnvs.cadmo.domain.model.Product
import com.lucasnvs.cadmo.ui.CadmoAppState
import com.lucasnvs.cadmo.ui.components.Product
import com.lucasnvs.cadmo.ui.components.bars.MainTopBar
import com.lucasnvs.cadmo.ui.components.bars.NavigationBottomBar
import com.lucasnvs.cadmo.ui.home.Loading
import com.lucasnvs.cadmo.ui.shared.ProductItemState
import com.lucasnvs.cadmo.ui.shared.Screen
import com.lucasnvs.cadmo.ui.shared.toModel
import com.lucasnvs.cadmo.ui.theme.CadmoTheme
@Composable
fun FavoriteScreen(
    modifier: Modifier = Modifier,
    appState: CadmoAppState,
    viewModel: FavoriteViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

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
        when {
            uiState.isLoading -> Loading(modifier = Modifier, innerPadding = innerPadding)
            uiState.favoriteProducts.isEmpty() -> EmptyContent(modifier = Modifier.padding(innerPadding))
            else ->
                Content(
                    favoriteItems = uiState.favoriteProducts,
                    onFavoriteClick = viewModel::onFavoriteClick,
                    modifier = Modifier.padding(innerPadding)
                )
        }
    }
}

@Composable
fun Content(
    favoriteItems: List<ProductItemState>,
    onFavoriteClick: (Product, Boolean) -> Unit,
    modifier: Modifier
) {
    Box(modifier = modifier
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
            items(favoriteItems) {  product ->
                Product(
                    modifier = Modifier,
                    product = product,
                    onBuyButtonClick = {},
                    onFavoriteButtonClick = { onFavoriteClick(product.toModel(), !product.isFavorite.value)} // provavelmente eu posso mudar todo o valor de ProductUI ou nao, porque nao e necessario o valor mutavel, ao modificar ele modifica o item e a lista por si só
                )
            }
        }
    }
}


@Composable
fun EmptyContent(modifier: Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF9F9F9))
    ) {
        Text(text = stringResource(id = R.string.empty_favorites_list), textAlign = TextAlign.Center)
    }
}

@Preview(showBackground = true)
@Composable
fun FavoriteScreenPreview() {
    CadmoTheme {
        FavoriteScreen(appState = CadmoAppState(rememberNavController()))
    }
}

//@Preview(showBackground = true)
//@Composable
//fun ContentPreview() {
//    Content()
//}

@Preview(showBackground = true)
@Composable
fun EmptyContentPreview() {
    EmptyContent(Modifier)
}