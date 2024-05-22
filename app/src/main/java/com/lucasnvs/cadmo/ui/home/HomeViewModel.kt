package com.lucasnvs.cadmo.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lucasnvs.cadmo.domain.model.Product
import com.lucasnvs.cadmo.domain.repository.ProductRepository
import com.lucasnvs.cadmo.ui.shared.ProductItemState
import com.lucasnvs.cadmo.ui.shared.toItemState
import com.lucasnvs.cadmo.ui.shared.toModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: ProductRepository,
) : ViewModel() {

    data class HomeUiState(
        var isFetchingSections: Boolean = false,
        val sections: Map<String, List<ProductItemState>> = mutableMapOf(),
    )

//    val HomeUiState.canAddToCartProduct: Boolean get() = isSignedIn

    var uiState by mutableStateOf(HomeUiState())
        private set

    init {
        fetchSections()
    }

    private fun addProductToFavorites(product: Product) {
        viewModelScope.launch {
            repository.addToFavorites(product)
        }
    }

    private fun deleteProductFromFavorites(product: Product) {
        viewModelScope.launch {
            repository.deleteFromFavorites(product)
        }
    }

    fun onItemFavoriteClick(sectionKey: String, index: Int) {
        val currentSections = uiState.sections.toMutableMap()
        if (!currentSections.containsKey(sectionKey)) return
        val itemList = currentSections[sectionKey] ?: return
        if (index !in itemList.indices) return

        val updatedItemList = itemList.toMutableList()
        val item = updatedItemList[index]

        if(item.isFavorite.value) {
            deleteProductFromFavorites(item.toModel())
            updatedItemList[index] = updatedItemList[index].copy(isFavorite = mutableStateOf(false))
        } else {
            addProductToFavorites(item.toModel())
            updatedItemList[index] = updatedItemList[index].copy(isFavorite = mutableStateOf(true))
        }

        currentSections[sectionKey] = updatedItemList
        uiState = uiState.copy(sections = currentSections)
    }

    private fun convertProductMapToProductItemUiStateMap(productMap: Map<String, List<Product>>): Map<String, List<ProductItemState>> {
        val homeItemUiStateMap = mutableMapOf<String, List<ProductItemState>>()

        viewModelScope.launch {
            val favoritesList = repository.getAllFavorites()

            homeItemUiStateMap.putAll(productMap.map { (key, productList) ->
                key to productList.map { product ->
                    val isFavorite = favoritesList.any { favorite -> favorite.id == product.id }
                    product.toItemState(isFavorite = mutableStateOf(isFavorite))
                }
            })
        }

        return homeItemUiStateMap
    }

    private var fetchJob: Job? = null

    fun fetchSections() {
        fetchJob?.cancel()
        uiState = uiState.copy(isFetchingSections = true)

        fetchJob = viewModelScope.launch {
            try {
                val sections = repository.getSections()

                delay(2000)
                uiState = uiState.copy(
                    isFetchingSections = false,
                    sections = convertProductMapToProductItemUiStateMap(sections),
                )

            } catch (ioe: IOException) {
                // Handle the error and notify the UI when appropriate.
                // val messages = getMessagesFromThrowable(ioe)
                // uiState = uiState.copy(userMessages = messages)
            }
        }
    }
}