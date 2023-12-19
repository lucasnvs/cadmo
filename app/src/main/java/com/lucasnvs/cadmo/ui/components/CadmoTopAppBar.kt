package com.lucasnvs.cadmo.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CadmoTopAppBar(modifier: Modifier = Modifier) {
    var searchValue by remember {
        mutableStateOf("")
    }

    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFF7B08B1),
            titleContentColor = Color.White,
        ),
        title = {
            SearchTextField(modifier = modifier.fillMaxWidth(0.95F))
        },
        actions = {
            IconButton(
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = Color(0xFF7B08B1),
                    contentColor = Color(0xFFAC62DA),
                ),
                onClick = { /* TODO */ },
                modifier = modifier.height(50.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.ShoppingCart,
                    contentDescription = "Carrinho",
                    modifier = modifier.size(35.dp)
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun CadmoTopAppBarPreview() {
    CadmoTopAppBar()
}