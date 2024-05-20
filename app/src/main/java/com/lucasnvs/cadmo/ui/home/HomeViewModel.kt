package com.lucasnvs.cadmo.ui.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lucasnvs.cadmo.data.Product
import com.lucasnvs.cadmo.data.ProductRepository
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

    data class HomeItemUiState(
        val isOnCart: MutableState<Boolean> = mutableStateOf(false),
        val name: String,
        val price: String,
        val img: String,
    )

    data class HomeUiState(
        var isFetchingSections: Boolean = false,
        val sections: Map<String, List<HomeItemUiState>> = mutableMapOf(),
    )

//    val HomeUiState.canAddToCartProduct: Boolean get() = isSignedIn

    var uiState by mutableStateOf(HomeUiState())
        private set

    init {
        fetchSections()
    }

    fun onItemCartClicked(sectionKey: String, index: Int) {
        val currentSections = uiState.sections.toMutableMap()

        if (!currentSections.containsKey(sectionKey)) return

        val itemList = currentSections[sectionKey] ?: return

        if (index !in itemList.indices) return

        val updatedItemList = itemList.toMutableList()
        updatedItemList[index] = updatedItemList[index].copy(isOnCart = mutableStateOf(!updatedItemList[index].isOnCart.value))

        currentSections[sectionKey] = updatedItemList

        uiState = uiState.copy(sections = currentSections)
    }

    private fun mapProduct(product: Product): HomeItemUiState {
        return HomeItemUiState(
            name = product.name,
            price = product.price,
            img = product.imgSrc,
        )
    }

    private fun convertProductMapToHomeItemUiStateMap(productMap: Map<String, List<Product>>): Map<String, List<HomeItemUiState>> {
        val homeItemUiStateMap = mutableMapOf<String, List<HomeItemUiState>>()

        for ((key, productList) in productMap) {
            val homeItemUiStateList = productList.map { product ->
                mapProduct(product)
            }
            homeItemUiStateMap[key] = homeItemUiStateList
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
                    sections = convertProductMapToHomeItemUiStateMap(sections),

                )

            } catch (ioe: IOException) {
                // Handle the error and notify the UI when appropriate.
                // val messages = getMessagesFromThrowable(ioe)
                // uiState = uiState.copy(userMessages = messages)
            }
        }
    }
}