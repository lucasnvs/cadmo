package com.lucasnvs.cadmo.ui.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ProfileViewModel() : ViewModel() {

    data class ProfileUiState(
        var currentUnsignedScreen: UnsignedScreen = UnsignedScreen.SigninScreen
    )

    var uiState by mutableStateOf(ProfileUiState())
        private set

    fun onSwitchUnsignedScreen() {
        if(uiState.currentUnsignedScreen == UnsignedScreen.SigninScreen) {
            uiState = uiState.copy(currentUnsignedScreen = UnsignedScreen.LoginScreen)
            return
        }
        uiState = uiState.copy(currentUnsignedScreen = UnsignedScreen.SigninScreen)
    }
}