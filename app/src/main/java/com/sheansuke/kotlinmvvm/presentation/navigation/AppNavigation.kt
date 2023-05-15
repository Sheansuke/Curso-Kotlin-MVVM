package com.sheansuke.kotlinmvvm.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sheansuke.kotlinmvvm.presentation.screens.login.LoginScreen
import com.sheansuke.kotlinmvvm.presentation.screens.profile.ProfileScreen
import com.sheansuke.kotlinmvvm.presentation.screens.signup.SignUpScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = AppScreen.Login.routeName) {
        composable(AppScreen.Login.routeName) {
            LoginScreen(navController)
        }

        composable(AppScreen.SignUp.routeName) {
            SignUpScreen(navController)
        }

        composable(AppScreen.Profile.routeName) {
            ProfileScreen(navController)
        }
    }
}