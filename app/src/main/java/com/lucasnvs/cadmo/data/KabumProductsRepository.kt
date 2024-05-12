package com.lucasnvs.cadmo.data

import com.lucasnvs.cadmo.model.Product
import com.lucasnvs.cadmo.network.KabumApi


data class DataSection(
    val key: String,
    val list: List<Product>
)

interface KabumProductsRepository {
    suspend fun getKabumProducts(): List<Product>
}

class NetworkKabumProductsRepository : KabumProductsRepository {
    override suspend fun getKabumProducts(): List<Product> {
        return KabumApi.retrofitService.getProducts().produtos
    }

    suspend fun getSections(): Map<String, List<Product>>  {
        val products = getKabumProducts()
        val sections = sectionIsOpenBox(products)


        val mapSections = mutableMapOf<String, List<Product>>()
        for (section in sections) {
            mapSections[section.key] = section.list
        }

        return mapSections
    }

    private fun sectionIsOpenBox(produtos: List<Product>): List<DataSection> {

        var listIsOpenBox = mutableListOf<Product>()
        var listNotIsOpenBox = mutableListOf<Product>()

        for (produto in produtos) {
            val check = produto.isOpenBox

            if(check) {
                listIsOpenBox.add(produto)
            } else {
                listNotIsOpenBox.add(produto)
            }
        }

        return listOf(
            DataSection(key = "É openbox", list = listIsOpenBox),
            DataSection(key = "Não é openbox", list = listNotIsOpenBox)
        )
    }

}