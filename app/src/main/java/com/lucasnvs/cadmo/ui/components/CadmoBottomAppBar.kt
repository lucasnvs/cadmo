package com.lucasnvs.cadmo.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import com.lucasnvs.cadmo.ui.screens.Screen

@Composable
fun CadmoBottomAppBar(
    modifier: Modifier = Modifier,
    currentDestination: NavDestination?,
    onNavigateToHome: () -> Unit,
    onNavigateToProfile: () -> Unit,
    onNavigateToDepartament: () -> Unit
) {

    var isInHome: Boolean = false
    var isInDept: Boolean = false
    var isInProfile: Boolean = false

    when(currentDestination?.route) {
        Screen.HomeScreen.route -> isInHome = true
        Screen.DepartamentScreen.route -> isInDept = true
        Screen.ProfileScreen.route -> isInProfile = true
    }

    BottomAppBar(
        modifier = modifier,
        containerColor = Color(0xFF7B08B1),
        content = {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp) // Adapte conforme necessário
            ) {
                TransparentIconButton(modifier, icon = Icons.Filled.Home, enabled = isInHome, name = "Home", onClick = onNavigateToHome)
                TransparentIconButton(modifier, icon = Icons.Filled.List, enabled = isInDept, name = "Departamentos", onClick = onNavigateToDepartament)
                TransparentIconButton(modifier, icon = Icons.Filled.Person, enabled = isInProfile, name = "Perfil", onClick = onNavigateToProfile)
            }
        }
    )

}

@Preview(showBackground = true)
@Composable
fun CadmoBottomAppBarPreview() {
    CadmoBottomAppBar(
        currentDestination = NavDestination(Screen.HomeScreen.route),
        onNavigateToHome = { /*TODO*/ },
        onNavigateToProfile = { /*TODO*/ },
        onNavigateToDepartament = {}
    )
}