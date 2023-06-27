package com.sheansuke.kotlinmvvm.presentation.screens.new_post.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.navigation.NavHostController
import com.sheansuke.kotlinmvvm.presentation.ui.theme.Red200

@Composable
fun NewPostTopBar(navController: NavHostController) {
    TopAppBar(
        backgroundColor = Red200
    ) {
        Row(
            modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .padding(start = 10.dp)
                    .clickable {
                        navController.popBackStack()
                    }, imageVector = Icons.Default.ArrowBack, contentDescription = "ArrowBack"
            )
            Text(
                modifier = Modifier.padding(start = 10.dp), text = "Nuevo Post"
            )
        }
    }
}