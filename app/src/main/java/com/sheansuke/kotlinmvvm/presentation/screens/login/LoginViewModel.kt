package com.sheansuke.kotlinmvvm.presentation.screens.login

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {
    var email: MutableState<String> = mutableStateOf("")
    var isValidEmail: MutableState<Boolean> = mutableStateOf(false)
    var emailErrorMsg: MutableState<String> = mutableStateOf("")


    var password: MutableState<String> = mutableStateOf("")
    var isValidPassword: MutableState<Boolean> = mutableStateOf(false)
    var passwordErrorMsg: MutableState<String> = mutableStateOf("")

    // BUTTON
    var isButtonEnabled: MutableState<Boolean> = mutableStateOf(false)

    fun validateEnabledButton () {
        isButtonEnabled.value = isValidEmail.value && isValidPassword.value
    }
    fun validEmail(){
        if (Patterns.EMAIL_ADDRESS.matcher(email.value).matches()){
            isValidEmail.value = true
            emailErrorMsg.value = ""
        } else {
            isValidEmail.value = false
            emailErrorMsg.value = "Email no valido"
        }
        validateEnabledButton()
    }

    fun validPassword(){
        if (password.value.length >= 6){
            isValidPassword.value = true
            passwordErrorMsg.value = ""
        } else {
            isValidPassword.value = false
            passwordErrorMsg.value = "Password no valida"
        }
        validateEnabledButton()
    }

}