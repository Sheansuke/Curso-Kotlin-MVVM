package com.sheansuke.kotlinmvvm.presentation.screens.signup

data class SignUpState(
    val username: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",

    // VALIDATIONS
    val isValidUserName: Boolean? = null,
    val isValidEmail: Boolean? = null,
    val isValidPassword: Boolean? = null,
    val isValidConfirmPassword: Boolean? = null,
    val isValidForm: Boolean = false
)
