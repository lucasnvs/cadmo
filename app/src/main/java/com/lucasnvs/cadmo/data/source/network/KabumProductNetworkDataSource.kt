package com.lucasnvs.cadmo.data.source.network

import javax.inject.Inject

data class DataSection(
    val key: String,
    val list: List<NetworkProduct>
)

class KabumProductNetworkDataSource : NetworkDataSource {

    override suspend fun loadProducts(): List<NetworkProduct> {
        return KabumApi.retrofitService.getProducts().produtos
    }

    override suspend fun saveProducts(tasks: List<NetworkProduct>) {
        TODO("Not yet implemented")
    }

    suspend fun getSections(): Map<String, List<NetworkProduct>>  {
        val products = loadProducts()
        val sections = sectionIsOpenBox(products)


        val mapSections = mutableMapOf<String, List<NetworkProduct>>()
        for (section in sections) {
            mapSections[section.key] = section.list
        }

        return mapSections
    }

    private fun sectionIsOpenBox(produtos: List<NetworkProduct>): List<DataSection> {

        var listIsOpenBox = mutableListOf<NetworkProduct>()
        var listNotIsOpenBox = mutableListOf<NetworkProduct>()

        for (produto in produtos) {
            val check = produto.isOpenBox

            if(check) {
                listIsOpenBox.add(produto)
            } else {
                listNotIsOpenBox.add(produto)
            }
        }

        return listOf(
            DataSection(key = "Mais procurados", list = listIsOpenBox),
            DataSection(key = "DESCONTOS DA SEMANA", list = listNotIsOpenBox)
        )
    }
}