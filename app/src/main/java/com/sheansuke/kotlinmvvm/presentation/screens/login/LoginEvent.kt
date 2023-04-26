package com.sheansuke.kotlinmvvm.presentation.screens.login

sealed class LoginEvent {
    data class InputEmail(val email: String): LoginEvent()
    data class InputPassword(val password: String): LoginEvent()

    object Login: LoginEvent()
}
