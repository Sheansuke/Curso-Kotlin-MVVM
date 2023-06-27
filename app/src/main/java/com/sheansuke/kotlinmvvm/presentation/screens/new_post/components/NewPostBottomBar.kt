package com.sheansuke.kotlinmvvm.presentation.screens.new_post.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sheansuke.kotlinmvvm.presentation.screens.new_post.NewPostEvent
import com.sheansuke.kotlinmvvm.presentation.screens.new_post.NewPostViewModel

@Composable
fun NewPostBottomBar(viewModel: NewPostViewModel = hiltViewModel()) {
    Button(modifier = Modifier
        .fillMaxWidth()
        .height(60.dp), onClick = {
        viewModel.onEvent(NewPostEvent.CreatePost)
    }) {
        Text(text = "Crear Post")
    }
}