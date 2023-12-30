package com.lucasnvs.cadmo.ui.screens

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.lucasnvs.cadmo.CadmoAppState
import com.lucasnvs.cadmo.ui.components.SearchTextField
import com.lucasnvs.cadmo.ui.components.bars.NavigationBottomBar
import com.lucasnvs.cadmo.ui.components.bars.MainTopBar
import com.lucasnvs.cadmo.ui.theme.CadmoTheme

val testlist = listOf(
    "Hardware",
    "Periféricos",
    "Mobile",
    "Casa Inteligente",
    "Games",
    "Automação",
    "Segurança",
    "Áudio",
    "Decoração",
    "Acessórios",
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
            ListItem(items = testlist)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DepartamentScreenPreview() {
    DepartamentScreen(appState = CadmoAppState( navController = rememberNavController() ))
}

@Composable
fun Item(
    modifier: Modifier = Modifier,
    name: String,
    onClick: () -> Unit = {}
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White)
            .clickable { onClick() }
            .padding(horizontal = 10.dp, vertical = 15.dp)

    ) {
        Text(text = name)
        Icon(imageVector = Icons.Filled.KeyboardArrowRight, contentDescription = "Abrir")
    }
}

@Preview(showBackground = true)
@Composable
fun ItemPreview() {
    CadmoTheme {
        Item(name = "Hardware")
    }
}

@Composable
fun ListItem(items: List<String>) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(1.dp)
    ) {
        items(items) {
            Item(name = it)
        }
    }
}
@Preview(showBackground = true)
@Composable
fun ListItemPreview() {
    CadmoTheme {
        ListItem(testlist)
    }
}