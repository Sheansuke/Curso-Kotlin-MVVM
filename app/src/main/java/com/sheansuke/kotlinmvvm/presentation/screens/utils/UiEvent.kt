package com.sheansuke.kotlinmvvm.presentation.screens.utils

// TODO: Falta integrar la nueva manera de leer eventos de interfaz
sealed class UiEvent {

    object Login: UiEvent()
    object Logout: UiEvent()
    object Loading: UiEvent()
    object Success: UiEvent()
    object Error: UiEvent()
}
