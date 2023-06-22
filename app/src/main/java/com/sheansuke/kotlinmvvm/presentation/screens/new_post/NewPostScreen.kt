package com.sheansuke.kotlinmvvm.presentation.screens.new_post

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable


@Composable
fun NewPostScreen(){
    Scaffold(
        topBar = {
                 NewPostTopBar()
        },
        content = {
                 NewPostContent()
        },
        bottomBar = {} 
    )
}