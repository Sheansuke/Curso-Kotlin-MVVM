package com.sheansuke.kotlinmvvm.presentation.screens.login

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
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authUseCase: AuthUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state: StateFlow<LoginState> = _state.asStateFlow()

    private val _eventFlow = MutableSharedFlow<UiEvent?>(1)
    val eventFlow: SharedFlow<UiEvent?> = _eventFlow.asSharedFlow()

    val currentUser = authUseCase.getCurrentUser()

    init {
        viewModelScope.launch {
            if (currentUser != null) { // SESSION INITIALIZED
                _eventFlow.emit(UiEvent.Success)
            }
        }
    }

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.InputEmail -> {
                _state.value = _state.value.copy(
                    email = event.email, isValidEmail = validateEmail(event.email)
                )
                validateForm()
            }

            is LoginEvent.InputPassword -> {
                _state.value = _state.value.copy(
                    password = event.password, isValidPassword = validatePassword(event.password)
                )
                validateForm()
            }

            is LoginEvent.Login -> {
                val (email, password) = _state.value
                viewModelScope.launch {
                    authUseCase.login(email, password).collect {
                        val eventResult = handleApiResult(it)
                        _eventFlow.emit(eventResult)
                    }

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