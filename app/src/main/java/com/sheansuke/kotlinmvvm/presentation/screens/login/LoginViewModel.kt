package com.sheansuke.kotlinmvvm.presentation.screens.login

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.sheansuke.kotlinmvvm.domain.model.Response
import com.sheansuke.kotlinmvvm.domain.usecase.auth.AuthUseCases
import com.sheansuke.kotlinmvvm.presentation.utils.validateEmail
import com.sheansuke.kotlinmvvm.presentation.utils.validatePassword
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authUseCases: AuthUseCases
) : ViewModel() {

    private val _loginState: MutableState<LoginState> = mutableStateOf(LoginState())
    val loginState: State<LoginState> = _loginState

    private val _loginFlow = MutableStateFlow<Response<FirebaseUser>?>(null)
    val loginFlow: StateFlow<Response<FirebaseUser>?> = _loginFlow


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

            is LoginEvent.Login -> {
                _loginFlow.value = Response.Loading
                viewModelScope.launch {
                    val result =
                        authUseCases.login(_loginState.value.email, _loginState.value.password)

                    _loginFlow.value = result
                }
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