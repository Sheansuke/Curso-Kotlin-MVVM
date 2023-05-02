package com.sheansuke.kotlinmvvm.domain.model

data class User(
    val username: String,
    val email: String,
    val password: String? = null
)
