package com.lucasnvs.cadmo.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lucasnvs.cadmo.ui.home.HomeViewModel
import com.lucasnvs.cadmo.ui.shared.ProductItemState
import java.util.Locale

@Composable
fun SectionProduct(
    modifier: Modifier,
    name: String,
    products: List<ProductItemState>,
    viewModel: HomeViewModel
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(
            space = 10.dp,
            alignment = Alignment.CenterVertically
        ),
        modifier = modifier.padding(top = 10.dp, bottom = 10.dp, start = 10.dp)
    ) {
        Text(
            text = name.uppercase(Locale.ROOT),
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight(600),
                color = Color(0xFF000000),
            )
        )

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(
                space = 5.dp,
                alignment = Alignment.CenterHorizontally
            )
        ) {
            items(products.size) { index ->
                Product(
                    modifier = modifier,
                    product = products[index],
                    onBuyButtonClick = {},
                    onFavoriteButtonClick = {
                        viewModel.onItemFavoriteClick( sectionKey = name, index )
                    }
                )
            }
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Preview(showBackground = true)
@Composable
fun SectionProductPreview() {
//    SectionProduct(
//        modifier = Modifier,
//        name = "Seção",
//        products = listOf(
//            HomeViewModel.HomeItemUiState(mutableStateOf(false), "Item", "20.00", "asd"),
//            HomeViewModel.HomeItemUiState(mutableStateOf(false), "Item", "20.00", "asd"),
//        )
//    )
}