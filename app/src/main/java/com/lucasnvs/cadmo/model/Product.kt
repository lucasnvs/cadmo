package com.lucasnvs.cadmo.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable()
data class Product(
    @SerialName(value = "codigo")
    val id: Int,
    @SerialName(value = "nome")
    val name: String,
    @SerialName(value = "preco_formatado")
    val price: String,
    @SerialName(value = "img")
    val imgSrc: String,
    @SerialName(value = "is_openbox")
    val isOpenBox: Boolean,
) {
}