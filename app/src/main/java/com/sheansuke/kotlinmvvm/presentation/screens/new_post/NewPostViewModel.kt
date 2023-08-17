package com.sheansuke.kotlinmvvm.presentation.screens.new_post

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sheansuke.kotlinmvvm.domain.use_case.posts.PostUseCase
import com.sheansuke.kotlinmvvm.presentation.screens.utils.UiEvent
import com.sheansuke.kotlinmvvm.presentation.screens.utils.handleApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NewPostViewModel @Inject constructor(
    private val postUseCase: PostUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(NewPostState())
    val state: StateFlow<NewPostState> = _state.asStateFlow()

    private val _eventFlow = MutableStateFlow<UiEvent?>(null)
    val eventFlow: StateFlow<UiEvent?> = _eventFlow

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
                        val uiEvent = handleApiResult(it)
                        _eventFlow.value = uiEvent
                    }
                }
            }
        }
    }
}