package com.sheansuke.kotlinmvvm.presentation.screens.new_post

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.sheansuke.kotlinmvvm.presentation.screens.new_post.components.NewPostBottomBar
import com.sheansuke.kotlinmvvm.presentation.screens.new_post.components.NewPostContent
import com.sheansuke.kotlinmvvm.presentation.screens.new_post.components.NewPostTopBar


@Composable
fun NewPostScreen(
    navController: NavHostController
) {
    Scaffold(topBar = {
        NewPostTopBar(navController)
    }, content = {
        NewPostContent()

        // EVENT FLOW HANDLER

//        when (viewModel.eventFlow.collectAsState().value) {
//            is UiEvent.Loading -> {
//                Box(
//                    modifier = Modifier.fillMaxSize(),
//                    contentAlignment = Alignment.Center
//                ) {
//                    CircularProgressIndicator()
//                }
//            }
//
//            is UiEvent.Success -> {
//                Toast.makeText(LocalContext.current, "Post created!", Toast.LENGTH_SHORT).show()
//            }
//
//            is UiEvent.Error -> {
//                Toast.makeText(LocalContext.current, "A error ocurred", Toast.LENGTH_SHORT).show()
//            }
//
//            else -> {}
//        }
    }, bottomBar = {
        NewPostBottomBar()
    })
}