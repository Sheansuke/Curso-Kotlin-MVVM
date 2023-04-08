package com.sheansuke.kotlinmvvm.presentation.screens.login

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import com.sheansuke.kotlinmvvm.presentation.screens.login.components.LoginBottomBar
import com.sheansuke.kotlinmvvm.presentation.screens.login.components.LoginContent


@Composable
fun LoginScreen() {
    Scaffold(
        topBar = {},
        content = {
            LoginContent()
        },
        bottomBar = {
            LoginBottomBar()
        }
    )
}

