package com.sheansuke.kotlinmvvm.presentation.screens.login

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.sheansuke.kotlinmvvm.presentation.screens.login.components.LoginBottomBar
import com.sheansuke.kotlinmvvm.presentation.screens.login.components.LoginContent


@Composable
fun LoginScreen(navController: NavHostController) {
    Scaffold(
        topBar = {},
        content = {
            LoginContent()
        },
        bottomBar = {
            LoginBottomBar(navController)
        }
    )
}

