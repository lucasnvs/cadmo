package com.lucasnvs.cadmo.data


data class Product(
    val id: Int,
    val name: String,
    val price: String,
    val imgSrc: String,
    val isOpenBox: Boolean,
)