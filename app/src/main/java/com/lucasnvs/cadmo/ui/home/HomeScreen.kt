package com.lucasnvs.cadmo.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lucasnvs.cadmo.domain.model.Product
import com.lucasnvs.cadmo.ui.CadmoAppState
import com.lucasnvs.cadmo.ui.components.SearchTextField
import com.lucasnvs.cadmo.ui.components.SectionProduct
import com.lucasnvs.cadmo.ui.components.bars.MainTopBar
import com.lucasnvs.cadmo.ui.components.bars.NavigationBottomBar
import com.lucasnvs.cadmo.ui.shared.ProductItemState
import com.lucasnvs.cadmo.ui.theme.CadmoTheme
import com.lucasnvs.cadmo.ui.shared.Screen
import com.lucasnvs.cadmo.ui.shared.toItemState
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    appState: CadmoAppState,
    viewModel: HomeViewModel = hiltViewModel(),
) {

    val uiState = viewModel.uiState

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        topBar = {
            MainTopBar(
                title = { SearchTextField(modifier = modifier.fillMaxWidth(0.95F)) },
                actionIcon = {
                    Icon(
                        imageVector = Icons.Filled.ShoppingCart,
                        contentDescription = "Carrinho",
                        modifier = modifier.size(35.dp)
                    )
                },
                actionOnClick = {
                if(appState.isSignedIn) {
                    scope.launch {
                        snackbarHostState.showSnackbar(message = "Está logado!", duration = SnackbarDuration.Short)
                    }
                } else {
                    scope.launch {
                        snackbarHostState.showSnackbar(message = " Não Está logado!", duration = SnackbarDuration.Short)
                    }
                }
            })
        },
        bottomBar = {
            NavigationBottomBar(
                currentDestination = appState.currentDestination,
                onNavigateToProfile = { appState.navigate(Screen.ProfileScreen) },
                onNavigateToDepartament = { appState.navigate(Screen.DepartamentScreen) },
                onNavigateToFavorite = {appState.navigate(Screen.FavoriteScreen)},
            )
        },

    ) { innerPadding ->
        if(uiState.isLoading) {
            Loading(modifier, innerPadding)
        } else {
            Content(
                sections = viewModel.getSections(),
                onFavoriteClick = viewModel::onFavoriteClick,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    CadmoTheme {
//        HomeScreen()
    }
}

@Composable
fun Content(
    sections: Map<String, List<ProductItemState>>,
    onFavoriteClick: (Product, Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier
            .fillMaxSize()
            .background(Color(0xFFF9F9F9))
    ) {

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(
                space = 10.dp,
                alignment = Alignment.CenterVertically
            ),
        ) {
            items(sections.entries.toList()) { (sectionName, products) ->
                SectionProduct(
                    modifier = Modifier,
                    name = sectionName,
                    products = products,
                    onItemFavoriteClick = onFavoriteClick
                )
            }
        }
    }
}

@Composable
fun Loading(modifier: Modifier, innerPadding: PaddingValues) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .padding(innerPadding)
            .fillMaxSize()
    ) {
        CircularProgressIndicator()
    }
}