package com.lucasnvs.cadmo.ui.components.buttons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TransparentIconButton(
    modifier: Modifier,
    enabled: Boolean = false,
    icon: ImageVector,
    name: String,
    onClick: () -> Unit
) {
    var contentColor = Color(0xFFAC62DA)
    if(enabled) contentColor = Color(0xFFFFFFFF)

    OutlinedButton(
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = contentColor
        ),
        border = BorderStroke(0.dp, Color.Transparent),
        onClick = onClick
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(
                space = 2.dp,
                alignment = Alignment.CenterVertically
            )
        ) {
            Icon(
                imageVector = icon,
                contentDescription = "Ir para $name",
                modifier = modifier.size(30.dp)
            )
            Text(
                text = name,
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 12.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight(600),
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TransparentIconButtonPreview() {
    Box(
        modifier = Modifier.background(Color.Blue).padding(5.dp)
    ) {
        TransparentIconButton(Modifier, icon = Icons.Filled.Home, name = "Home") {}
    }
}
