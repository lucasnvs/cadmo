package com.lucasnvs.cadmo.ui.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTextField(
    modifier: Modifier = Modifier
) {

    val enabled = true
    val singleLine = true
    val colors = OutlinedTextFieldDefaults.colors(
        unfocusedContainerColor = Color.White,
        focusedContainerColor = Color.White,
        focusedBorderColor = Color.White,
        unfocusedTextColor = Color.Black,
        focusedTextColor = Color.Black,
        focusedLeadingIconColor = Color(0xFFC3C1C1),
        unfocusedLeadingIconColor = Color(0xFFC3C1C1),
        focusedPlaceholderColor = Color(0xFFC3C1C1),
        unfocusedPlaceholderColor = Color(0xFFC3C1C1),
    )

    val interactionSource = remember {
        MutableInteractionSource()
    }

    var searchValue by remember {
        mutableStateOf("")
    }

    BasicTextField(
        enabled = enabled,
        interactionSource = interactionSource,
        textStyle = TextStyle(
            fontSize = 17.sp,
        ),
        cursorBrush = SolidColor(Color(0xFF7B08B1)),
        singleLine = singleLine,
        value = searchValue,
        onValueChange = {
            searchValue = it
        },
        modifier = modifier.height(35.dp),
    ) {
        TextFieldDefaults.DecorationBox(
            value = searchValue,
            innerTextField = it,
            enabled = enabled,
            singleLine = singleLine,
            visualTransformation = VisualTransformation.None,
            interactionSource = interactionSource,
            placeholder = { Text("Buscar...") },
            leadingIcon = { Icon(imageVector = Icons.Filled.Search, contentDescription = "Buscar") },
            colors = colors,
            contentPadding = TextFieldDefaults.contentPaddingWithoutLabel(
                top = 0.dp,
                bottom = 0.dp,
            ),
            container = {
                OutlinedTextFieldDefaults.ContainerBox(
                    isError = false,
                    enabled = enabled,
                    interactionSource = interactionSource,
                    colors = colors
                )
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SearchTextFieldPreview() {
    SearchTextField()
}

