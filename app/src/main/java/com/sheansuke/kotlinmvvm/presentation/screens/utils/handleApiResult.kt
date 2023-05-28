package com.sheansuke.kotlinmvvm.presentation.screens.utils

import com.sheansuke.kotlinmvvm.domain.model.Resource

fun handleApiResult(result: Resource<Any>): UiEvent {
    return when(result) {
        is Resource.Loading -> UiEvent.Loading
        is Resource.Success -> UiEvent.Success
        is Resource.Error -> UiEvent.Error
    }
}