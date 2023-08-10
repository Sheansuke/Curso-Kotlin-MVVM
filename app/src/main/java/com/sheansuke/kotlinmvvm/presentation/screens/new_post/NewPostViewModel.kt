package com.sheansuke.kotlinmvvm.presentation.screens.new_post

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sheansuke.kotlinmvvm.domain.use_case.posts.PostUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NewPostViewModel @Inject constructor(
    private val postUseCase: PostUseCase
) : ViewModel() {

    private val _state: MutableState<NewPostState> = mutableStateOf(
        NewPostState()
    )
    val state: State<NewPostState> = _state

    fun onEvent(event: NewPostEvent) {
        when (event) {
            is NewPostEvent.InputGameName -> {
                _state.value = _state.value.copy(
                    name = event.gameName
                )
            }

            is NewPostEvent.InputGameDescription -> {
                _state.value = _state.value.copy(
                    description = event.gameDescription
                )
            }

            is NewPostEvent.SelectGameCategory -> {
                _state.value = _state.value.copy(
                    category = event.gameCategory
                )
            }

            is NewPostEvent.PickGameImageUri -> {
                _state.value = _state.value.copy(
                    imageUri = event.gameImageUri
                )
            }

            is NewPostEvent.CreatePost -> {
                val newPost = _state.value.toPostModel()
                val newImageUri = _state.value.imageUri
                viewModelScope.launch {
                    postUseCase.create(newPost, newImageUri).collect {
                        Log.i("result", it.toString())
                    }
                }
            }
        }
    }
}