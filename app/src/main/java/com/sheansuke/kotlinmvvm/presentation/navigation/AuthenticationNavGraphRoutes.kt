package com.sheansuke.kotlinmvvm.presentation.navigation

sealed class AuthenticationNavGraphRoutes(val routeName: String) {
    object Login : AuthenticationNavGraphRoutes("/login")
    object SignUp : AuthenticationNavGraphRoutes("/signup")
}
