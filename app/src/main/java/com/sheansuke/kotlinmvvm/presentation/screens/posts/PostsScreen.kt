package com.sheansuke.kotlinmvvm.presentation.screens.posts

import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.sheansuke.kotlinmvvm.presentation.navigation.EditProfileNavGraphRoutes

@Composable
fun PostsScreen(navController: NavHostController) {
    Scaffold(
        topBar = {},
        content = {
            Text(text = "Posts Sreen")
        },
        bottomBar = {},
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.padding(bottom = 70.dp, end = 10.dp),
                onClick = {
                    navController.navigate(EditProfileNavGraphRoutes.NewPost.routeName)
                }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add New Post")
            }
        }
    )
}