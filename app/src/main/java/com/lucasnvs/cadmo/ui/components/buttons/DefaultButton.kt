package com.lucasnvs.cadmo.ui.components.buttons

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun DefaultButton(
    modifier: Modifier,
    onClick: () -> Unit,
    content: @Composable RowScope.() -> Unit
) {
    Button(
        modifier = modifier,
        elevation = ButtonDefaults.buttonElevation(2.dp),
        shape = RoundedCornerShape(2.dp),
        onClick = onClick,
        content = content
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultButtonPreview() {
    DefaultButton(modifier = Modifier, {}) {}
}