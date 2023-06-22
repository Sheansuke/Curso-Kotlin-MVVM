package com.sheansuke.kotlinmvvm.presentation.screens.new_post

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sheansuke.kotlinmvvm.presentation.ui.theme.Red200
import com.sheansuke.kotlinmvvm.presentation.ui.theme.Red500

@Composable
fun NewPostTopBar() {
    TopAppBar(
        backgroundColor = Red200
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .padding(start = 10.dp),
                imageVector = Icons.Default.ArrowBack, contentDescription = "ArrowBack"
            )
            Text(
                modifier = Modifier
                    .padding(start = 10.dp),
                text = "Nuevo Post"
            )
        }
    }
}