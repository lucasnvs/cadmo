package com.lucasnvs.cadmo.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lucasnvs.cadmo.CadmoAppState
import com.lucasnvs.cadmo.ui.components.CadmoBottomAppBar
import com.lucasnvs.cadmo.ui.components.CadmoTopAppBar
import com.lucasnvs.cadmo.ui.components.SectionProduct
import com.lucasnvs.cadmo.ui.theme.CadmoTheme
import com.lucasnvs.cadmo.ui.viewmodel.HomeViewModel
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    appState: CadmoAppState,
    viewModel: HomeViewModel = viewModel(),
) {
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }


    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        topBar = {
            CadmoTopAppBar( actionOnClick = {
                if(viewModel.uiState.isSignedIn) {
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
            CadmoBottomAppBar(
                currentDestination = appState.currentDestination,
                onNavigateToProfile = { appState.navigate(Screen.ProfileScreen) },
                onNavigateToDepartament = { appState.navigate(Screen.DepartamentScreen) },
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
                items(viewModel.uiState.sections.entries.toList()) { (sectionName, products) ->
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
//        HomeScreen()
    }
}