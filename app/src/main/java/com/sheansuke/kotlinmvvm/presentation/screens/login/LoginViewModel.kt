package com.sheansuke.kotlinmvvm.presentation.screens.login

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.sheansuke.kotlinmvvm.presentation.utils.validateEmail
import com.sheansuke.kotlinmvvm.presentation.utils.validatePassword
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    val _loginState: MutableState<LoginState> = mutableStateOf(LoginState())
    val loginState: State<LoginState> = _loginState


    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.InputEmail -> {
                _loginState.value = loginState.value.copy(
                    email = event.email,
                    isValidEmail = validateEmail(event.email)
                )
                validateForm()
            }

            is LoginEvent.InputPassword -> {
                _loginState.value = loginState.value.copy(
                    password = event.password,
                    isValidPassword = validatePassword(event.password)
                )
                validateForm()
            }
        }
    }

    fun validateForm() {
        if (_loginState.value.isValidEmail == true && _loginState.value.isValidPassword == true) {
            _loginState.value = loginState.value.copy(
                isValidForm = true
            )
        } else {
            _loginState.value = loginState.value.copy(
                isValidForm = false
            )
        }
    }

}