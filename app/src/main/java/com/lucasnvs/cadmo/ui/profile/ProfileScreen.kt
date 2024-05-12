package com.lucasnvs.cadmo.ui.profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.lucasnvs.cadmo.ui.CadmoAppState
import com.lucasnvs.cadmo.R
import com.lucasnvs.cadmo.ui.components.ListRedirectOption
import com.lucasnvs.cadmo.ui.components.bars.NavigationBottomBar
import com.lucasnvs.cadmo.ui.components.bars.MainTopBar
import com.lucasnvs.cadmo.ui.home.HomeViewModel
import com.lucasnvs.cadmo.ui.shared.Screen
import com.lucasnvs.cadmo.ui.theme.CadmoTheme
import com.lucasnvs.cadmo.ui.theme.LightGrayColor
import com.lucasnvs.cadmo.ui.theme.PrincipalColor

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    appState: CadmoAppState,
    viewModel: ProfileViewModel = viewModel(),
    ) {
    Scaffold(
        topBar = {
            MainTopBar(title = { Text(text = "Meu Perfil", fontSize = 19.sp) })
        },
        bottomBar = {
            NavigationBottomBar(
                currentDestination = appState.currentDestination,
                onNavigateToHome = { appState.popBackStack() },
                onNavigateToDepartament = { appState.navigate(Screen.DepartamentScreen) },
                onNavigateToFavorite = { appState.navigate(Screen.FavoriteScreen) },
            )
        },
        ) { innerPadding ->
        if(!appState.isSignedIn) {
            when(viewModel.uiState.currentUnsignedScreen) {
                UnsignedScreen.LoginScreen -> Login(modifier.padding(innerPadding), onSwitchUnsignedScreen = { viewModel.onSwitchUnsignedScreen() })
                UnsignedScreen.SignIn -> SignIn(modifier.padding(innerPadding), onSwitchUnsignedScreen = { viewModel.onSwitchUnsignedScreen() })
            }
        } else {
            Profile(modifier = modifier.padding(innerPadding))
        }
    }

}

@Composable
fun Profile(modifier: Modifier = Modifier) {
    val iconShoppingBasket = ImageVector.vectorResource(id = R.drawable.baseline_shopping_basket_24)
    val profileOptions = listOf(
        "Meus Pedidos" to iconShoppingBasket,
        "Favoritos" to Icons.Filled.Favorite,
        "Configurações" to Icons.Filled.Settings
    )

    Box(modifier = modifier) {
        ListRedirectOption(items = profileOptions)
    }
}
@Composable
fun Login(
    modifier: Modifier = Modifier,
    onSwitchUnsignedScreen: () -> Unit
) {
    val modFullWidth = Modifier.fillMaxWidth()

    Column(
        verticalArrangement = Arrangement.spacedBy(space = 8.dp, alignment = Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(10.dp)
            .fillMaxSize()
    ) {
        Text(text = "FAZER LOGIN", color = PrincipalColor, fontSize = 19.sp, letterSpacing = 2.sp)
        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text("E-mail ou CPF", color = LightGrayColor) },
            modifier = modFullWidth
        )
        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text("Senha", color = LightGrayColor) },
            modifier = modFullWidth
        )
        Button(
            shape = RoundedCornerShape(2.dp),
            onClick = { /*TODO*/ },
            modifier = modFullWidth.height(45.dp)
        ) {
            Text(text = "ENTRAR")
        }
        Spacer(modFullWidth.height(10.dp))
        Divider(color = LightGrayColor, thickness = 2.dp)
        Spacer(modFullWidth.height(10.dp))

        Row {
            Text(text = "Novo na Cadmo?")
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = "CADASTRE-SE",
                fontWeight = FontWeight(600),
                color = PrincipalColor,
                textDecoration = TextDecoration.Underline,
                letterSpacing = 1.sp,
                modifier = Modifier.clickable { onSwitchUnsignedScreen() }
            )
        }
    }
}
@Composable
fun SignIn(
    modifier: Modifier = Modifier,
    onSwitchUnsignedScreen: () -> Unit
) {
    val modFullWidth = Modifier.fillMaxWidth()

    Column(
        verticalArrangement = Arrangement.spacedBy(space = 8.dp, alignment = Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(10.dp)
            .fillMaxSize()
    ) {
        Text(text = "CRIAR CONTA", color = PrincipalColor, fontSize = 19.sp, letterSpacing = 2.sp)
        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text("Nome Completo *", color = LightGrayColor) },
            modifier = modFullWidth
        )
        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text("CPF *", color = LightGrayColor) },
            modifier = modFullWidth
        )
        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text("Data de nascimento *", color = LightGrayColor) },
            modifier = modFullWidth
        )
        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text("Crie sua senha *", color = LightGrayColor) },
            modifier = modFullWidth
        )
        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text("Confirme sua senha *", color = LightGrayColor) },
            modifier = modFullWidth
        )
        Button(
            shape = RoundedCornerShape(2.dp),
            onClick = { /*TODO*/ },
            modifier = modFullWidth.height(45.dp)
        ) {
            Text(text = "CONTINUAR")
        }
        Spacer(modFullWidth.height(10.dp))
        Divider(color = LightGrayColor, thickness = 2.dp)
        Spacer(modFullWidth.height(10.dp))

        Row {
            Text(text = "Já possui cadastro?")
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = "ENTRAR",
                fontWeight = FontWeight(600),
                color = PrincipalColor,
                textDecoration = TextDecoration.Underline,
                letterSpacing = 1.sp,
                modifier = Modifier.clickable { onSwitchUnsignedScreen() }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    CadmoTheme {
        ProfileScreen(appState = CadmoAppState(rememberNavController()))
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    CadmoTheme {
        Profile()
    }
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    CadmoTheme {
        Login( onSwitchUnsignedScreen = {})
    }
}

@Preview(showBackground = true)
@Composable
fun SignInPreview() {
    CadmoTheme {
        SignIn(onSwitchUnsignedScreen = {})
    }
}