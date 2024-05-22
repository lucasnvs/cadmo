package com.lucasnvs.cadmo.data.source.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable()
data class  NetworkProduct(
    @SerialName(value = "codigo")
    val id: Long,
    @SerialName(value = "nome")
    val name: String,
    @SerialName(value = "preco_formatado")
    val price: String,
    @SerialName(value = "img")
    val imgSrc: String,
)