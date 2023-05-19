package com.sheansuke.kotlinmvvm.presentation.screens.profile

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sheansuke.kotlinmvvm.domain.model.User
import com.sheansuke.kotlinmvvm.domain.use_case.auth.AuthUseCase
import com.sheansuke.kotlinmvvm.domain.use_case.users.UsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authUseCase: AuthUseCase,
    private val usersUseCase: UsersUseCase
) : ViewModel() {

    private val _state: MutableState<User> = mutableStateOf(User())
    val state: State<User> = _state

    init {
        viewModelScope.launch {
            usersUseCase.getUserById(authUseCase.getCurrentUser()!!.uid).collect {
                _state.value = it
            }

        }
    }

    fun onEvent(event: ProfileEvent) {
        when (event) {
            is ProfileEvent.Logout -> {
                authUseCase.logout()
            }
        }
    }

}