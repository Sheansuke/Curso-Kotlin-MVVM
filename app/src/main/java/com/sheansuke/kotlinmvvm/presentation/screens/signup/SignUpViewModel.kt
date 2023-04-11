package com.sheansuke.kotlinmvvm.presentation.screens.signup

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor() : ViewModel() {
    data class UiState(
        val username: String
    )

    private val _signUpState: MutableState<UiState> = mutableStateOf(UiState(""))
    var signUpState: State<UiState> = _signUpState

    fun changeUserName(newUserName: String) {
       val newState = signUpState.value.copy(
            username = newUserName
        )
        _signUpState.value = newState
    }

}