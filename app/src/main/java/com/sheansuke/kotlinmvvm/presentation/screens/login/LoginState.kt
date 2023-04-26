package com.sheansuke.kotlinmvvm.presentation.screens.login

data class LoginState(
    val email: String = "",
    val password: String = "",

    // VALIDATIONS
    val isValidEmail: Boolean? = null,
    val isValidPassword: Boolean? = null,
    val isValidForm: Boolean = false,
)
