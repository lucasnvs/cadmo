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
        var isLoading: Boolean = false,
        val bestRatedList: List<Product> = emptyList(),
        val moreSearched: List<Product> = emptyList(),
        val ninjaOfferList: List<Product> = emptyList(),
    )

//    val HomeUiState.canAddToCartProduct: Boolean get() = isSignedIn

    var uiState by mutableStateOf(HomeUiState())
        private set

    init {
        viewModelScope.launch {
            uiState = uiState.copy(bestRatedList = repository.getBestRated())
            uiState = uiState.copy(moreSearched = repository.getMoreSearched())
            uiState = uiState.copy(ninjaOfferList = repository.getNinjaOffer())
        }

        uiState = uiState.copy(isLoading = false)
    }

    fun getSections(): Map<String, List<ProductItemState>> {
        return mapOf(
            "MAIS PROCURADOS" to uiState.moreSearched.toItemState(),
            "MELHOR AVALIADOS" to uiState.bestRatedList.toItemState(),
            "OFERTA NINJA" to uiState.ninjaOfferList.toItemState()
        )
    }

    fun onFavoriteClick(product: Product, favorited: Boolean) = viewModelScope.launch {
        if(favorited) {
            repository.addToFavorites(product)
        } else {
            repository.deleteFromFavorites(product)
        }
    }

//    fun onItemFavoriteClick(sectionKey: String, index: Int) {
//        val currentSections = uiState.sections.toMutableMap()
//        if (!currentSections.containsKey(sectionKey)) return
//        val itemList = currentSections[sectionKey] ?: return
//        if (index !in itemList.indices) return
//
//        val updatedItemList = itemList.toMutableList()
//        val item = updatedItemList[index]
//
//        if(item.isFavorite.value) {
//            deleteProductFromFavorites(item.toModel())
//            updatedItemList[index] = updatedItemList[index].copy(isFavorite = mutableStateOf(false))
//        } else {
//            addProductToFavorites(item.toModel())
//            updatedItemList[index] = updatedItemList[index].copy(isFavorite = mutableStateOf(true))
//        }
//
//        currentSections[sectionKey] = updatedItemList
//        uiState = uiState.copy(sections = currentSections)
//    }
}