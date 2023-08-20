package com.sheansuke.kotlinmvvm.presentation.screens.new_post

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.sheansuke.kotlinmvvm.presentation.components.LoadingIndicator
import com.sheansuke.kotlinmvvm.presentation.screens.new_post.components.NewPostBottomBar
import com.sheansuke.kotlinmvvm.presentation.screens.new_post.components.NewPostContent
import com.sheansuke.kotlinmvvm.presentation.screens.new_post.components.NewPostTopBar


@Composable
fun NewPostScreen(
    navController: NavHostController,
    viewModel: NewPostViewModel = hiltViewModel()
) {
    val stateFlow = viewModel.eventFlow.collectAsStateWithLifecycle(null)
    Scaffold(topBar = {
        NewPostTopBar(navController)
    }, content = {
        NewPostContent()

        // EVENT FLOW HANDLER
        LoadingIndicator(
            state = stateFlow,
            successMessage = "Post Created",
            errorMessage = "An error ocurred",
        )
    }, bottomBar = {
        NewPostBottomBar()
    })
}