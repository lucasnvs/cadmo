package com.lucasnvs.cadmo.ui.components.bars

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import com.lucasnvs.cadmo.ui.components.buttons.TransparentIconButton
import com.lucasnvs.cadmo.ui.screens.Screen
import com.lucasnvs.cadmo.ui.theme.PrincipalColor

@Composable
fun NavigationBottomBar(
    modifier: Modifier = Modifier,
    currentDestination: NavDestination?,
    onNavigateToHome: () -> Unit,
    onNavigateToProfile: () -> Unit,
    onNavigateToDepartament: () -> Unit
) {

    var isInHome: Boolean = false
    var isInDept: Boolean = false
    var isInProfile: Boolean = false
    var isInCart: Boolean = false

    when(currentDestination?.route) {
        Screen.HomeScreen.route -> isInHome = true
        Screen.DepartamentScreen.route -> isInDept = true
        Screen.ProfileScreen.route -> isInProfile = true
    }

    BottomAppBar(
        modifier = modifier,
        containerColor = PrincipalColor,
        content = {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                TransparentIconButton(modifier.weight(1f), icon = Icons.Filled.Home, enabled = isInHome, name = "Home", onClick = onNavigateToHome)
                TransparentIconButton(modifier.weight(1f), icon = Icons.Filled.List, enabled = isInDept, name = "Departamentos", onClick = onNavigateToDepartament)
                TransparentIconButton(modifier.weight(1f), icon = Icons.Filled.ShoppingCart, enabled = isInCart, name = "Carrinho", onClick = { })
                TransparentIconButton(modifier.weight(1f), icon = Icons.Filled.Person, enabled = isInProfile, name = "Perfil", onClick = onNavigateToProfile)
            }
        }
    )

}

@Preview(showBackground = true)
@Composable
fun NavigationBottomBarPreview() {
    NavigationBottomBar(
        currentDestination = NavDestination(Screen.HomeScreen.route),
        onNavigateToHome = { },
        onNavigateToProfile = { },
        onNavigateToDepartament = {}
    )
}