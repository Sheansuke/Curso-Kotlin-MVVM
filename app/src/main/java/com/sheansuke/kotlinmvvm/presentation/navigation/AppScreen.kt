package com.sheansuke.kotlinmvvm.presentation.navigation

sealed class AppScreen(val routeName: String) {
    object Login : AppScreen("/login")
    object SignUp : AppScreen("/signup")
    object Profile : AppScreen("/profile")
    object ProfileEdit : AppScreen("/profile/edit/{user}") {
        fun passUser(user: String) = "/profile/edit/${user}"
    }
}
