package com.sheansuke.kotlinmvvm.presentation.screens.signup

import android.util.Patterns
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor() : ViewModel() {

    private val _signUpState: MutableState<SignUpState> = mutableStateOf(SignUpState(""))
    var signUpState: State<SignUpState> = _signUpState

    // EVENTS -------------------------------------------------------
    fun onEvent(event: SignUpEvent){
        when(event){
            is SignUpEvent.InputUserName -> {
                _signUpState.value = signUpState.value.copy(
                    username = event.username,
                    isValidUserName = validateUsername(event.username)
                )
            }

            is SignUpEvent.InputEmail -> {
                _signUpState.value = signUpState.value.copy(
                    email = event.email,
                    isValidEmail = validateEmail(event.email)
                )
            }

            is SignUpEvent.InputPassword -> {
                _signUpState.value = signUpState.value.copy(
                    password = event.password,
                    isValidPassword = validatePassword(event.password)
                )
            }

            is SignUpEvent.InputConfirmPassword -> {
                _signUpState.value = signUpState.value.copy(
                    confirmPassword = event.confirmPassword,
                    isValidConfirmPassword = validateConfirmPassword(event.confirmPassword)
                )
            }

        }
    }


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

    fun validateConfirmPassword(confirmPassword: String): Boolean {
        return if (confirmPassword == _signUpState.value.password) true else false
    }

}