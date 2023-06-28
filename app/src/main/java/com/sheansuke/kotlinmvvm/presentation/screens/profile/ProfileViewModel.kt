package com.sheansuke.kotlinmvvm.presentation.screens.profile

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sheansuke.kotlinmvvm.domain.model.User
import com.sheansuke.kotlinmvvm.domain.use_case.auth.AuthUseCase
import com.sheansuke.kotlinmvvm.domain.use_case.users.UsersUseCase
import com.sheansuke.kotlinmvvm.presentation.screens.utils.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authUseCase: AuthUseCase,
    private val usersUseCase: UsersUseCase
) : ViewModel() {

    private val _state: MutableState<User> = mutableStateOf(User())
    val state: State<User> = _state

    private val _eventFlow = MutableStateFlow<UiEvent?>(null)
    val eventFlow = _eventFlow

    init {
        viewModelScope.launch {
            eventFlow.value = UiEvent.Loading
            usersUseCase.getUserById(authUseCase.getCurrentUser()!!.uid).collect {
                _state.value = it
                eventFlow.value = UiEvent.Success
            }
        }
    }

    fun onEvent(event: ProfileEvent) {
        when (event) {
            is ProfileEvent.Logout -> {
                authUseCase.logout()
                _eventFlow.value = UiEvent.Logout
            }
        }
    }

}