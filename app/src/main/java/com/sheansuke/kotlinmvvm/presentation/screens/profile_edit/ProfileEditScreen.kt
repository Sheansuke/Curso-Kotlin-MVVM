package com.sheansuke.kotlinmvvm.presentation.screens.profile_edit

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.sheansuke.kotlinmvvm.presentation.screens.profile_edit.components.ProfileEditContent

@Composable
fun ProfileEditScreen(navController: NavHostController) {
    Scaffold(
        topBar = {},
        content = {
            ProfileEditContent()
        },
        bottomBar = {}
    )
}