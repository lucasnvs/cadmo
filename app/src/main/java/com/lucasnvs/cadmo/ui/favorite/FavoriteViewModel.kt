package com.lucasnvs.cadmo.ui.favorite

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
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
  private val repository: ProductRepository
) : ViewModel() {

    data class FavoriteUiState(
        var isLoading: Boolean = false,
        var favoriteProducts: List<ProductItemState> = mutableListOf()
    )

    var uiState by mutableStateOf(FavoriteUiState())
        private set

    init {
        fetchFavorite()
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

    fun onItemFavoriteClick(index: Int) {

        val updatedItemList = uiState.favoriteProducts.toMutableList()
        val item = updatedItemList[index]

        if(item.isFavorite.value) {
            deleteProductFromFavorites(item.toModel())
            updatedItemList.removeAt(index)
        }

        uiState = uiState.copy(favoriteProducts = updatedItemList)
    }


    private var fetchJob: Job? = null

    fun fetchFavorite() {
        fetchJob?.cancel()
        uiState = uiState.copy(isLoading = true)

        fetchJob = viewModelScope.launch {
            try {
                val favoriteProducts = repository.getAllFavorites()

                uiState = uiState.copy(
                    isLoading = false,
                    favoriteProducts = favoriteProducts.toItemState(mutableStateOf(true))
                )

            } catch (ioe: IOException) {
                // Handle the error and notify the UI when appropriate.
                // val messages = getMessagesFromThrowable(ioe)
                // uiState = uiState.copy(userMessages = messages)
            }
        }
    }
}
