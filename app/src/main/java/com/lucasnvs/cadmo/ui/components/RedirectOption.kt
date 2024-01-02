package com.lucasnvs.cadmo.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lucasnvs.cadmo.ui.theme.CadmoTheme

@Composable
fun RedirectOption(
    modifier: Modifier = Modifier,
    label: @Composable () -> Unit = {},
    labelIcon: @Composable () -> Unit = {},
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
        Row() {
            labelIcon()
            label()
        }
        Icon(imageVector = Icons.Filled.KeyboardArrowRight, contentDescription = "Abrir")
    }
}

@Preview(showBackground = true)
@Composable
fun RedirectOptionPreview() {
    CadmoTheme {
        RedirectOption(label = { Text(text = "Hardware") })
    }
}

@Composable
fun ListRedirectOption(items: List<Pair<String, ImageVector?>>) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(1.dp)
    ) {
        items(items) { (title, icon) ->
            RedirectOption(
                label = { Text(text = title) },
                labelIcon = {
                    if(icon != null) {
                        Icon(imageVector = icon, contentDescription = "Icone de $title")
                    }
                }
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun ListRedirectOptionPreview() {
    val testlist: List<Pair<String, ImageVector?>> = listOf(
        "Hardware" to null,
        "Periféricos" to null,
        "Mobile" to null,
        "Casa Inteligente" to null,
        "Games" to null,
        "Automação" to null,
        "Segurança" to null,
        "Áudio" to null,
        "Decoração" to null,
        "Acessórios" to null,
    )

    CadmoTheme {
        ListRedirectOption(testlist)
    }
}