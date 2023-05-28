package com.sheansuke.kotlinmvvm.presentation.screens.login

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sheansuke.kotlinmvvm.domain.use_case.auth.AuthUseCase
import com.sheansuke.kotlinmvvm.presentation.screens.utils.UiEvent
import com.sheansuke.kotlinmvvm.presentation.screens.utils.handleApiResult
import com.sheansuke.kotlinmvvm.presentation.utils.validateEmail
import com.sheansuke.kotlinmvvm.presentation.utils.validatePassword
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authUseCase: AuthUseCase
) : ViewModel() {

    private val _state: MutableState<LoginState> = mutableStateOf(LoginState())
    val state: State<LoginState> = _state

    private val _eventFlow = MutableStateFlow<UiEvent?>(null)
    val eventFlow: StateFlow<UiEvent?> = _eventFlow

    val currentUser = authUseCase.getCurrentUser()

    init {
        viewModelScope.launch {
            if (currentUser != null) { // SESSION INITIALIZED
                _eventFlow.value = UiEvent.Success
            }
        }
    }

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.InputEmail -> {
                _state.value = state.value.copy(
                    email = event.email,
                    isValidEmail = validateEmail(event.email)
                )
                validateForm()
            }

            is LoginEvent.InputPassword -> {
                _state.value = state.value.copy(
                    password = event.password,
                    isValidPassword = validatePassword(event.password)
                )
                validateForm()
            }

            is LoginEvent.Login -> {
                    _eventFlow.value = UiEvent.Loading
                viewModelScope.launch {
                    val (email, password) = _state.value
                    val result = authUseCase.login(email, password)
                    val eventResult = handleApiResult(result)
                    _eventFlow.value = eventResult
                }
            }
        }
    }

    fun validateForm() {
        if (_state.value.isValidEmail == true && _state.value.isValidPassword == true) {
            _state.value = state.value.copy(
                isValidForm = true
            )
        } else {
            _state.value = state.value.copy(
                isValidForm = false
            )
        }
    }

}