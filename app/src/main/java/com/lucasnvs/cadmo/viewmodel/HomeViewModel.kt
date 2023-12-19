package com.lucasnvs.cadmo.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Job

class HomeViewModel(
    // private val repository: NewsRepository,
) : ViewModel() {

    data class HomeItemUiState(
        val isOnCart: Boolean = false,
        val name: String,
        val price: String,
    )

    data class HomeUiState(
        val isFetchingProducts: Boolean = false,
        val isSignedIn: Boolean = false,
        val productItems: List<HomeItemUiState> = listOf()
    )

    val HomeUiState.canAddToCartProduct: Boolean get() = isSignedIn

    var uiState by mutableStateOf(HomeUiState())
        private set

    private var fetchJob: Job? = null

//    fun fetchArticles(category: String) {
//        fetchJob?.cancel()
//        fetchJob = viewModelScope.launch {
//            try {
//                val newsItems = repository.newsItemsForCategory(category)
//                uiState = uiState.copy(newsItems = newsItems)
//            } catch (ioe: IOException) {
//                // Handle the error and notify the UI when appropriate.
//                val messages = getMessagesFromThrowable(ioe)
//                uiState = uiState.copy(userMessages = messages)
//            }
//        }
//    }
}