package com.sheansuke.kotlinmvvm.presentation.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.sheansuke.kotlinmvvm.presentation.screens.login.LoginScreen
import com.sheansuke.kotlinmvvm.presentation.screens.profile.ProfileScreen
import com.sheansuke.kotlinmvvm.presentation.screens.profile_edit.ProfileEditScreen
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

        composable(
            AppScreen.ProfileEdit.routeName,
            arguments = listOf(
                navArgument("user", { type = NavType.StringType })
            )
        ) {
            ProfileEditScreen(navController)
        }
    }
}