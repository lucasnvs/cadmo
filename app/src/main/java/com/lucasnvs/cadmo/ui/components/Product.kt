package com.lucasnvs.cadmo.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.lucasnvs.cadmo.ui.theme.LightGrayColor
import com.lucasnvs.cadmo.ui.theme.PrincipalColor
import com.lucasnvs.cadmo.ui.viewmodel.HomeViewModel

@Composable
fun Product(modifier: Modifier, product: HomeViewModel.HomeItemUiState, onCartButtonClick: () -> Unit) {

    fun handleClickBuyButton() {

    }

    var cartIconColor = LightGrayColor
    if(product.isOnCart) cartIconColor = PrincipalColor

    val painter = rememberAsyncImagePainter(product.img)

    Box(modifier = modifier
        .shadow(elevation = 3.dp)
        .width(260.dp)
        .height(165.dp)
        .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 2.dp))
        .padding(5.dp)
    ) {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(
                space = 5.dp,
                alignment = Alignment.CenterVertically
            )
        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .height(25.dp),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = Color.Transparent,
                        contentColor = cartIconColor,
                    ),
                    onClick = onCartButtonClick,
                    modifier = modifier.size(25.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "Carrinho",
                        modifier = modifier.size(25.dp)
                    )
                }
            }

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
                    painter = painter,
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
                            text = product.price,
                            style = TextStyle(
                                fontSize = 11.sp,
                                fontFamily = FontFamily.SansSerif,
                                fontWeight = FontWeight(700),
                                color = PrincipalColor,
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
                    modifier = modifier.fillMaxWidth(),
                    elevation = ButtonDefaults.buttonElevation(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PrincipalColor,
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
        .background(Color(0xFFFCFCCC))
        .padding(10.dp)) {
        Product(modifier = Modifier, product = HomeViewModel.HomeItemUiState(
            isOnCart = false,
            "Monitor Gamer",
            price = "R$ 1149,99",
            img = "https://images8.kabum.com.br/produtos/fotos/444038/monitor-gamer-lg-ultragear-27-full-hd-144hz-1ms-ips-hdmi-e-displayport-hdr-10-99-srgb-freesync-premium-vesa-27gn65r_1684763831_m.jpg"
        ), {})
    }
}