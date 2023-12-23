package com.lucasnvs.cadmo.ui.components.bars

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lucasnvs.cadmo.ui.theme.LightPrincipalColor
import com.lucasnvs.cadmo.ui.theme.PrincipalColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(
        modifier: Modifier = Modifier,
        title: @Composable () -> Unit,
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = PrincipalColor,
            titleContentColor = Color.White,
        ),
        title = title
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(
    modifier: Modifier = Modifier,
    title: @Composable () -> Unit,
    actionIcon: @Composable () -> Unit,
    actionOnClick: () -> Unit
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = PrincipalColor,
            titleContentColor = Color.White,
        ),
        title = title,
        actions = {
            IconButton(
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = PrincipalColor,
                    contentColor = LightPrincipalColor,
                ),
                onClick = actionOnClick,
                modifier = modifier.size(50.dp)
            ) {
                actionIcon()
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun MainTopBarPreview() {
    MainTopBar(
        title = { Text(text = "Titulo", fontSize = 19.sp) },
        actionIcon = { Icon(
            imageVector = Icons.Filled.ShoppingCart,
            contentDescription = "Carrinho",
            modifier = Modifier.size(35.dp)
        ) },
        actionOnClick = {}
    )
}