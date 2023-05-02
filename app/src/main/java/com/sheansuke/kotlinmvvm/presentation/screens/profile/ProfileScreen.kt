package com.sheansuke.kotlinmvvm.presentation.screens.profile

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.sheansuke.kotlinmvvm.presentation.screens.profile.components.ProfileContent

@Composable
fun ProfileScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            Text(text = "User Profile Screen")
        },
        content = {
                  ProfileContent(navController)
        },
        bottomBar = {}
    )
}