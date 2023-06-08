package com.sheansuke.kotlinmvvm.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.sheansuke.kotlinmvvm.presentation.screens.profile_edit.ProfileEditScreen

fun NavGraphBuilder.editProfileNavGraph(navController: NavHostController) {
    navigation(
        route = RootGraph.EDITPROFILE,
        startDestination = EditProfileNavGraphRoutes.ProfileEdit.routeName
    ) {
        composable(
            EditProfileNavGraphRoutes.ProfileEdit.routeName,
            arguments = listOf(
                navArgument("user", { type = NavType.StringType })
            )
        ) {
            ProfileEditScreen(navController)
        }
    }
}

sealed class EditProfileNavGraphRoutes(val routeName: String) {
    object ProfileEdit : EditProfileNavGraphRoutes("/profile/edit/{user}") {
        fun passUser(user: String) = "/profile/edit/${user}"
    }
}