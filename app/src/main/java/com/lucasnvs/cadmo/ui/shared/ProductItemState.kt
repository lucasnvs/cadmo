package com.lucasnvs.cadmo.ui.shared

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class ProductItemState(
    val id: Long,
    val isFavorite: MutableState<Boolean> = mutableStateOf(false),
    val name: String,
    val price: String,
    val img: String,
)
