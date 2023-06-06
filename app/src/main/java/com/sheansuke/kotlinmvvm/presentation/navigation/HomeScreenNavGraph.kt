package com.sheansuke.kotlinmvvm.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sheansuke.kotlinmvvm.presentation.screens.my_posts.MyPostsScreen
import com.sheansuke.kotlinmvvm.presentation.screens.posts.PostsScreen
import com.sheansuke.kotlinmvvm.presentation.screens.profile.ProfileScreen


@Composable
fun HomeScreenNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        route = RootGraph.HOME,
        startDestination = HomeScreenNavGraphRoutes.Posts.routeName
    ) {
        composable(HomeScreenNavGraphRoutes.Posts.routeName) {
            PostsScreen()
        }

        composable(HomeScreenNavGraphRoutes.MyPosts.routeName) {
            MyPostsScreen()
        }


        composable(HomeScreenNavGraphRoutes.Profile.routeName) {
            ProfileScreen(navController)
        }

    }
}