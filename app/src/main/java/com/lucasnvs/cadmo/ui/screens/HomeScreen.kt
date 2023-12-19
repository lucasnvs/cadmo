package com.lucasnvs.cadmo.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.lucasnvs.cadmo.map
import com.lucasnvs.cadmo.ui.components.CadmoBottomAppBar
import com.lucasnvs.cadmo.ui.components.CadmoTopAppBar
import com.lucasnvs.cadmo.ui.components.SectionProduct
import com.lucasnvs.cadmo.ui.theme.CadmoTheme
import com.lucasnvs.cadmo.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(),
    navController: NavController,
) {
    Scaffold(
        topBar = {
            CadmoTopAppBar()
        },
        bottomBar = {
            CadmoBottomAppBar(
                onNavigateToProfile = { navController.navigate(Screen.ProfileScreen.route) },
                onNavigateToDepartament = { navController.navigate(Screen.DepartamentScreen.route) },
                onNavigateToHome = {}
            )
        },

    ) { innerPadding ->
        Box(
            modifier
                .padding(innerPadding)
        ) {

            if(viewModel.uiState.isFetchingProducts) {
                CircularProgressIndicator(Modifier.align(Alignment.Center))
            }

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(
                    space = 10.dp,
                    alignment = Alignment.CenterVertically
                ),
            ) {
                items(map.entries.toList()) { (products, sectionName) ->
                    SectionProduct(modifier = modifier, name = sectionName, products = products)
                }

            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    CadmoTheme {
        HomeScreen(navController = rememberNavController())
    }
}