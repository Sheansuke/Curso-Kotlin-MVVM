package com.sheansuke.kotlinmvvm.domain.model

sealed class Resource<out T>(val data: T? = null, val exception: Exception? = null) {
    object Loading : Resource<Nothing>()
    class Success<out T>(data: T? = null, exception: Exception? = null) : Resource<T>(data, exception)
    class Error<out T>(data: T? = null, exception: Exception) : Resource<T>(data, exception)
}
