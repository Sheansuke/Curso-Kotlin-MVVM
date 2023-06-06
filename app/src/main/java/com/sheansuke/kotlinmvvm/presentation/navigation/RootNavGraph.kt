package com.sheansuke.kotlinmvvm.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.sheansuke.kotlinmvvm.presentation.screens.home.HomeScreen
import com.sheansuke.kotlinmvvm.presentation.screens.profile_edit.ProfileEditScreen

@Composable
fun RootNavGraph(navController: NavHostController) {
    NavHost(navController = navController,
        startDestination = RootGraph.AUTHENTICATION,
        route = RootGraph.ROOT,
    ) {

        authenticationNavGraph(navController)

        composable(RootGraph.HOME) {
            HomeScreen()
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