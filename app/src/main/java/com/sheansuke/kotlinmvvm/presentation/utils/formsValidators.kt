package com.sheansuke.kotlinmvvm.presentation.utils

import android.util.Patterns

// VALIDATIONS -------------------------------------------------------
fun validateUsername(username: String): Boolean {
    return if (username.length >= 4) true else false
}
fun validateEmail(email: String): Boolean {
    return if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) true else false
}

fun validatePassword(password: String): Boolean {
    return if (password.length >= 8) true else false
}

fun validateConfirmPassword(password: String, confirmPassword: String): Boolean {
    return if (confirmPassword == password) true else false
}