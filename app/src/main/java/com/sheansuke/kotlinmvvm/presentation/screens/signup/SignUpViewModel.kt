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

    private val _uiState: MutableState<UiState> = mutableStateOf(UiState(""))
    var uiState: State<UiState> = _uiState

    fun changeUserName(newUserName: String) {
       val newState = uiState.value.copy(
            username = newUserName
        )

        _uiState.value = newState
    }

}