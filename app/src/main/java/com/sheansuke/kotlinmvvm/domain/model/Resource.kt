package com.sheansuke.kotlinmvvm.domain.model

import java.lang.Exception

sealed class Resource<out T> {
    object Loading: Resource<Nothing>()

    data class Success<out T>(val data: T? = null): Resource<T>()
    data class Error<out T>(val exception: Exception): Resource<T>()
}
