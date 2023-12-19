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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CadmoBottomAppBar(
    modifier: Modifier = Modifier,
    onNavigateToHome: () -> Unit,
    onNavigateToProfile: () -> Unit,
    onNavigateToDepartament: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(color = Color(0xFF7B08B1))
            .padding(5.dp)
        ,
        horizontalArrangement = Arrangement.spacedBy(
            space = 10.dp,
            alignment = Alignment.CenterHorizontally
        )
    ) {

        TransparentIconButton(modifier, icon = Icons.Filled.Home, name = "Home", onNavigateToHome)
        TransparentIconButton(modifier, icon = Icons.Filled.List, name = "Departamentos", onNavigateToDepartament )
        TransparentIconButton(modifier, icon = Icons.Filled.Person, name = "Perfil", onNavigateToProfile )
    }
}

@Preview(showBackground = true)
@Composable
fun CadmoBottomAppBarPreview() {
    CadmoBottomAppBar(onNavigateToProfile = { /*TODO*/ }, onNavigateToDepartament = {}, onNavigateToHome = {})
}