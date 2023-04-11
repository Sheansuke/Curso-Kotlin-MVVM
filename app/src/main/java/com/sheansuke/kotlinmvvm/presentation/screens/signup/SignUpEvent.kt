package com.sheansuke.kotlinmvvm.presentation.screens.signup

sealed class SignUpEvent {
    data class InputUserName(val username: String = ""): SignUpEvent()
    data class InputEmail(val email: String = ""): SignUpEvent()
    data class InputPassword(val password: String = ""): SignUpEvent()
    data class InputConfirmPassword(val confirmPassword: String = ""): SignUpEvent()
}
