package com.sheansuke.kotlinmvvm.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sheansuke.kotlinmvvm.presentation.screens.login.LoginScreen
import com.sheansuke.kotlinmvvm.presentation.screens.signup.SignUpScreen

fun NavGraphBuilder.authenticationNavGraph(
    navController: NavHostController
) {
    navigation(
        route = RootGraph.AUTHENTICATION,
        startDestination = AuthenticationNavGraphRoutes.Login.routeName
    ) {
        composable(AuthenticationNavGraphRoutes.Login.routeName) {
            LoginScreen(navController)
        }

        composable(AuthenticationNavGraphRoutes.SignUp.routeName) {
            SignUpScreen(navController)
        }
    }
}

sealed class AuthenticationNavGraphRoutes(val routeName: String) {
    object Login : AuthenticationNavGraphRoutes("/login")
    object SignUp : AuthenticationNavGraphRoutes("/signup")
}
