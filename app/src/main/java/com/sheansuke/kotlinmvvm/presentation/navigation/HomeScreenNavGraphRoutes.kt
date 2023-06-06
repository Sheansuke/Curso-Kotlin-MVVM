package com.sheansuke.kotlinmvvm.presentation.navigation

sealed class HomeScreenNavGraphRoutes(val routeName: String) {
    object Posts: HomeScreenNavGraphRoutes("/posts")
    object MyPosts: HomeScreenNavGraphRoutes("/my_posts")
    object Profile: HomeScreenNavGraphRoutes("/profile")
}
