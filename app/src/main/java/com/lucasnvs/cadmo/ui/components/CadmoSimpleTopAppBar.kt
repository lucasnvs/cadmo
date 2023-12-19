package com.lucasnvs.cadmo.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CadmoSimpleTopAppBar(
        modifier: Modifier = Modifier,
        title: String,
    ) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
        containerColor = Color(0xFF7B08B1),
        titleContentColor = Color.White,
    ),
    title = { Text(text = title, fontSize = 19.sp) }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CadmoSimpleTopAppBar(
    modifier: Modifier = Modifier,
    title: String,
    actionIcon: ImageVector,
    actionOnClick: () -> Unit
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFF7B08B1),
            titleContentColor = Color.White,
        ),
        title = { Text(text = title, fontSize = 19.sp) },
        actions = {
            IconButton(
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = Color(0xFF7B08B1),
                    contentColor = Color(0xFFAC62DA),
                ),
                onClick = actionOnClick
            ) {
                Icon(
                    imageVector = actionIcon,
                    contentDescription = "Carrinho",

                    modifier = modifier.size(35.dp)
                )
            }
        }
    )
}
