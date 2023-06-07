package com.sheansuke.kotlinmvvm.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
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
        startDestination = HomeScreenBottomBar.Posts.route
    ) {
        composable(HomeScreenBottomBar.Posts.route) {
            PostsScreen()
        }

        composable(HomeScreenBottomBar.MyPosts.route) {
            MyPostsScreen()
        }


        composable(HomeScreenBottomBar.Profile.route) {
            ProfileScreen(navController)
        }

    }
}

sealed class HomeScreenBottomBar(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Posts : HomeScreenBottomBar(
        "/posts",
        "Posts",
        Icons.Default.List
    )

    object MyPosts : HomeScreenBottomBar(
        "/my_posts",
        "My Posts",
        Icons.Default.Favorite
    )

    object Profile : HomeScreenBottomBar(
        "/profile",
        "Profile",
        Icons.Default.Face
    )
}
