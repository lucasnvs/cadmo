package com.lucasnvs.cadmo.ui.components.bars

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination
import com.lucasnvs.cadmo.ui.components.buttons.TransparentIconButton
import com.lucasnvs.cadmo.ui.shared.Screen
import com.lucasnvs.cadmo.ui.theme.PrincipalColor

@Composable
fun NavigationBottomBar(
    modifier: Modifier = Modifier,
    currentDestination: NavDestination?,
    onNavigateToHome: () -> Unit = {},
    onNavigateToProfile: () -> Unit = {},
    onNavigateToFavorite: () -> Unit = {},
    onNavigateToDepartament: () -> Unit = {}
) {

    var isInHome: Boolean = false
    var isInDept: Boolean = false
    var isInProfile: Boolean = false
    var isInFavorite: Boolean = false

    when(currentDestination?.route) {
        Screen.HomeScreen.route -> isInHome = true
        Screen.DepartamentScreen.route -> isInDept = true
        Screen.FavoriteScreen.route -> isInFavorite = true
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
            ) {
                TransparentIconButton(modifier.weight(1f), icon = Icons.Rounded.Home, enabled = isInHome, name = "Home", onClick = onNavigateToHome)
                TransparentIconButton(modifier.weight(1f), icon = Icons.Filled.List, enabled = isInDept, name = "Departamentos", onClick = onNavigateToDepartament)
                TransparentIconButton(modifier.weight(1f), icon = Icons.Rounded.Favorite, enabled = isInFavorite, name = "Favoritos", onClick = onNavigateToFavorite)
                TransparentIconButton(modifier.weight(1f), icon = Icons.Rounded.Person, enabled = isInProfile, name = "Perfil", onClick = onNavigateToProfile)
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
        onNavigateToFavorite = { },
        onNavigateToDepartament = {}
    )
}