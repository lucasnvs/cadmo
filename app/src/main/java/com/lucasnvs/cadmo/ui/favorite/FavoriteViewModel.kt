package com.lucasnvs.cadmo.ui.favorite

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lucasnvs.cadmo.R
import com.lucasnvs.cadmo.domain.model.Product
import com.lucasnvs.cadmo.domain.repository.ProductRepository
import com.lucasnvs.cadmo.ui.shared.ProductItemState
import com.lucasnvs.cadmo.ui.shared.toItemState
import com.lucasnvs.cadmo.ui.util.Async
import com.lucasnvs.cadmo.ui.util.WhileUiSubscribed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
  private val repository: ProductRepository,
) : ViewModel() {

    data class FavoriteUiState(
        var isLoading: Boolean = false,
        var favoriteProducts: List<ProductItemState> = emptyList(),
        val userMessage: Int? = null
    )

    private val _userMessage: MutableStateFlow<Int?> = MutableStateFlow(null)
    private val _isLoading = MutableStateFlow(false)
    private val _favoriteProductsAsync = repository.getAllFavoritesStream()
        .map { Async.Success(it) }
        .catch<Async<List<Product>>> { emit(Async.Error(R.string.loading_favorites_error))}

    val uiState: StateFlow<FavoriteUiState> = combine(
        _isLoading, _userMessage, _favoriteProductsAsync
    ) { isLoading, userMessage, favoriteProductsAsync ->
        when (favoriteProductsAsync) {
            Async.Loading -> {
                FavoriteUiState(isLoading = true)
            }
            is Async.Error -> {
                FavoriteUiState(userMessage = favoriteProductsAsync.errorMessage)
            }
            is Async.Success -> {
                FavoriteUiState(
                    favoriteProducts = favoriteProductsAsync.data.toItemState(mutableStateOf(true)),
                    isLoading = isLoading,
                    userMessage = userMessage
                )
            }
        }
    }
        .stateIn(
            scope = viewModelScope,
            started = WhileUiSubscribed,
            initialValue = FavoriteUiState(isLoading = true)
        )

    fun onFavoriteClick(product: Product, favorited: Boolean) = viewModelScope.launch {
        if(favorited) {
            repository.addToFavorites(product)
        } else {
            repository.deleteFromFavorites(product)
        }
    }
}
