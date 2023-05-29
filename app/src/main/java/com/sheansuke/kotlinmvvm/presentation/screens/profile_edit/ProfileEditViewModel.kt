package com.sheansuke.kotlinmvvm.presentation.screens.profile_edit

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sheansuke.kotlinmvvm.domain.model.User
import com.sheansuke.kotlinmvvm.domain.use_case.users.UsersUseCase
import com.sheansuke.kotlinmvvm.presentation.screens.utils.UiEvent
import com.sheansuke.kotlinmvvm.presentation.screens.utils.handleApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileEditViewModel @Inject constructor(
    private val usersUseCase: UsersUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state: MutableState<User> = mutableStateOf(User())
    val state: State<User> = _state

    private val _eventFlow = MutableStateFlow<UiEvent?>(null)
    val eventFlow: StateFlow<UiEvent?> = _eventFlow

    init {
        val userString = savedStateHandle.get<String>("user")
        val user = _state.value.fromJson(userString!!)
        _state.value = user
    }

    fun updateUser() {
        _eventFlow.value = UiEvent.Loading
        viewModelScope.launch {
            val result = usersUseCase.update(_state.value)
            result.collect {
                val eventResult = handleApiResult(it)
                _eventFlow.value = eventResult
            }

        }
    }

    fun onEvent(event: ProfileEditEvent) {
        when (event) {
            is ProfileEditEvent.InputUserName -> {
                _state.value = _state.value.copy(
                    username = event.username
                )
            }

            is ProfileEditEvent.PickImage -> {
                _eventFlow.value = UiEvent.Loading
                viewModelScope.launch {
                    val result = usersUseCase.uploadUserImage(event.imageUri)
                    result.collect {
                        val eventResult = handleApiResult(it)
                        _eventFlow.value = eventResult
                        _state.value = _state.value.copy(
                            image = it.data
                        )
                    }
                updateUser()
                }

            }

            is ProfileEditEvent.UpdateUser -> {
                updateUser()
            }
        }
    }
}