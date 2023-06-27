package com.sheansuke.kotlinmvvm.presentation.screens.new_post

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class NewPostViewModel @Inject constructor() : ViewModel() {

    private val _state: MutableState<NewPostState> = mutableStateOf(
        NewPostState()
    )
    val state: State<NewPostState> = _state

    fun onEvent(event: NewPostEvent) {
        when (event) {
            is NewPostEvent.InputGameName -> {
                _state.value = _state.value.copy(
                    gameName = event.gameName
                )
            }

            is NewPostEvent.InputGameDescription -> {
                _state.value = _state.value.copy(
                    gameDescription = event.gameDescription
                )
            }

            is NewPostEvent.SelectGameCategory -> {
                _state.value = _state.value.copy(
                    gameCategory = event.gameCategory
                )
            }

            is NewPostEvent.PickGameImageUri -> {
                _state.value = _state.value.copy(
                    gameImageUri = event.gameImageUri
                )
            }

            is NewPostEvent.CreatePost -> {
                Log.i("newpost", _state.toString())
            }
        }
    }
}