package com.lucasnvs.cadmo.ui.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.lucasnvs.cadmo.ui.home.HomeViewModel

class ProfileViewModel() : ViewModel() {

    data class ProfileUiState(
        var currentUnsignedScreen: UnsignedScreen = UnsignedScreen.SignIn
    )

    var uiState by mutableStateOf(ProfileUiState())
        private set

    fun onSwitchUnsignedScreen() {
        if(uiState.currentUnsignedScreen == UnsignedScreen.SignIn) {
            uiState = uiState.copy(currentUnsignedScreen = UnsignedScreen.LoginScreen)
            return
        }
        uiState = uiState.copy(currentUnsignedScreen = UnsignedScreen.SignIn)
    }
}