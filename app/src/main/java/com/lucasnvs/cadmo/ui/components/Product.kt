package com.lucasnvs.cadmo.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lucasnvs.cadmo.R


data class DataProduct(
    val name: String,
    val price: Double,
)

@Composable
fun Product(modifier: Modifier, product: DataProduct) {

    fun handleClickBuyButton() {

    }

    fun handleClickCartButton() {

    }


    Box(modifier = modifier
        .shadow(elevation = 4.dp, spotColor = Color(0x40000000), ambientColor = Color(0x40000000))
        .width(260.dp)
        .height(140.dp)
        .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 2.dp))
        .padding(5.dp)
    ) {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(
                space = 10.dp,
                alignment = Alignment.CenterVertically
            )
        ) {
            Row(
                modifier = modifier,
                horizontalArrangement = Arrangement.spacedBy(
                    space = 8.dp,
                    alignment = Alignment.CenterHorizontally
                )
            ) {
                Image(
                    modifier = modifier
                        .width(90.dp)
                        .height(90.dp),
                    painter = painterResource(id = R.drawable.product_example),
                    contentDescription = "Foto de ${product.name}",
                    contentScale = ContentScale.FillBounds
                )
                Column(
                    modifier = modifier.height(90.dp),
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text(
                        text = product.name,
                        style = TextStyle(
                            fontSize = 11.sp,
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight(400),
                            color = Color(0xFF000000),
                        ),
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )

                    Column {
                        Text(
                            text = "R$${product.price}",
                            style = TextStyle(
                                fontSize = 11.sp,
                                fontFamily = FontFamily.SansSerif,
                                fontWeight = FontWeight(700),
                                color = Color(0xFF7B08B1),
                            )
                        )
                        Text(
                            text = "À vista no PIX",
                            style = TextStyle(
                                fontSize = 6.sp,
                                fontFamily = FontFamily.SansSerif,
                                fontWeight = FontWeight(400),
                                color = Color(0xFF000000),

                                )
                        )
                    }
                }
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(
                    space = 5.dp,
                    alignment = Alignment.CenterHorizontally
                )
            ) {
                Button(
                    modifier = modifier,
                    elevation = ButtonDefaults.buttonElevation(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF7B08B1),
                        contentColor = Color(0xFFFFFFFF)
                    ),
                    shape = RoundedCornerShape(2.dp),
                    onClick = { handleClickCartButton() }
                ) {
                    Icon(
                        imageVector = Icons.Filled.ShoppingCart,
                        contentDescription = "Carrinho",
                        modifier = modifier.size(ButtonDefaults.IconSize)
                    )
                }

                Button(
                    modifier = modifier.width(200.dp),
                    elevation = ButtonDefaults.buttonElevation(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF7B08B1),
                        contentColor = Color(0xFFFFFFFF)
                    ),
                    shape = RoundedCornerShape(2.dp),
                    onClick = { handleClickBuyButton() }
                ) {
                    Text(
                        text = "COMPRAR",
                        style = TextStyle(
                            fontSize = 10.sp,
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight(600),
                            color = Color(0xFFFFFFFF),
                        )
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductPreview() {
    Box(modifier = Modifier
        .background(Color(0xFFFCFCFC))
        .padding(10.dp)) {
        Product(Modifier, DataProduct("Placa de Vídeo RTX 4060 EAGLE OC Gigabyte NVIDIA GeForce, 8GB GDUAL novo novo novo novo", 1954.99))
    }
}