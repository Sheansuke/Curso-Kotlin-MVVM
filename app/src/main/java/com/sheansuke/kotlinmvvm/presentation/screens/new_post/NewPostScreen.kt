package com.sheansuke.kotlinmvvm.presentation.screens.new_post

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.sheansuke.kotlinmvvm.presentation.screens.new_post.components.NewPostBottomBar
import com.sheansuke.kotlinmvvm.presentation.screens.new_post.components.NewPostContent
import com.sheansuke.kotlinmvvm.presentation.screens.new_post.components.NewPostTopBar


@Composable
fun NewPostScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            NewPostTopBar(navController)
        }, content = {
            NewPostContent(it)
        }, bottomBar = {
            NewPostBottomBar()
        })
}