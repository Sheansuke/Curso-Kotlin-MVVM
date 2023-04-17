package com.sheansuke.kotlinmvvm.domain.usecase.auth

data class AuthUseCases (
    val getCurrentUser: GetCurrentUser,
    val login: Login
)