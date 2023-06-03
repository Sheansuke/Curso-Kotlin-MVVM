package com.sheansuke.kotlinmvvm.presentation.navigation

sealed class HomeBottomBarGraph(val routeName: String) {
    object Post : HomeBottomBarGraph("/post")
    object MyPost : HomeBottomBarGraph("/mypost")
    object Profile : HomeBottomBarGraph("/profile")
}