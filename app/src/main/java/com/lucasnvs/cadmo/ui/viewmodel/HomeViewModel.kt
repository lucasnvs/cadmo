package com.lucasnvs.cadmo.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lucasnvs.cadmo.data.NetworkKabumProductsRepository
import com.lucasnvs.cadmo.model.Product
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.io.IOException

class HomeViewModel(
    private val repository: NetworkKabumProductsRepository = NetworkKabumProductsRepository(),
) : ViewModel() {

    data class HomeItemUiState(
        val isOnCart: Boolean = true,
        val name: String,
        val price: String,
        val img: String,
    )

    data class HomeUiState(
        var isFetchingProducts: Boolean = false,
        val isSignedIn: Boolean = false,
        val productItems: List<HomeItemUiState> = listOf(),
        val sections: Map<String, List<HomeItemUiState>> = mutableMapOf()
    )

    val HomeUiState.canAddToCartProduct: Boolean get() = isSignedIn

    var uiState by mutableStateOf(HomeUiState())
        private set

    init {
        fetchProducts()
    }

    private fun mapProduct(product: Product): HomeItemUiState {
        return HomeItemUiState(
            name = product.name,
            price = product.price,
            img = product.imgSrc,
        )
    }

    private fun mapAllProducts(productsList: List<Product>): List<HomeItemUiState> {
        return productsList.map { mapProduct(it) }
    }

    private fun sectionIsOpenBox(produtos: List<Product>): Map<String, List<HomeItemUiState>> {
        val map = mutableMapOf<String, MutableList<HomeItemUiState>>()
        map["É openbox"] = mutableListOf()
        map["Não é openbox"] = mutableListOf()

        for (produto in produtos) {
            val check = produto.isOpenBox

            if(check) {
                map["É openbox"]!!.add(mapProduct(produto))
            } else {
                map["Não é openbox"]!!.add(mapProduct(produto))
            }
        }

        return map
    }

    private var fetchJob: Job? = null

    fun fetchProducts() {
        fetchJob?.cancel()
        uiState = uiState.copy(isFetchingProducts = true)

        fetchJob = viewModelScope.launch {
            try {
                val productItems = repository.getKabumProducts()

                uiState = uiState.copy(
                    isFetchingProducts = false,
                    sections = sectionIsOpenBox(productItems),
                    productItems = mapAllProducts(productItems)
                )

            } catch (ioe: IOException) {
                // Handle the error and notify the UI when appropriate.
                // val messages = getMessagesFromThrowable(ioe)
                // uiState = uiState.copy(userMessages = messages)
            }
        }
    }
}