package com.lucasnvs.cadmo.ui.shared

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.lucasnvs.cadmo.domain.model.Product


fun Product.toItemState(isFavorite: MutableState<Boolean> = mutableStateOf(false)) = ProductItemState(
    id = id,
    name = name,
    price = price,
    img = imgSrc,
    isFavorite = isFavorite
)

fun List<Product>.toItemState(isFavorite: MutableState<Boolean> = mutableStateOf(false)) = map { product -> product.toItemState(isFavorite) }

fun ProductItemState.toModel() = Product(
    id = id,
    name = name,
    price = price,
    imgSrc = img
)