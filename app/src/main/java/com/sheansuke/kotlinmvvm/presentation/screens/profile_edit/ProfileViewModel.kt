package com.sheansuke.kotlinmvvm.presentation.screens.profile_edit

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.sheansuke.kotlinmvvm.domain.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state: MutableState<User> = mutableStateOf(User())
    val state: State<User> = _state

    init {
        val userString = savedStateHandle.get<String>("user")
        val user = _state.value.fromJson(userString!!)
        _state.value = user
    }

}