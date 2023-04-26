package com.sheansuke.kotlinmvvm.domain.use_case.auth

data class AuthUseCase(
    val getCurrentUser: GetCurrentUser,
    val login: Login
)
