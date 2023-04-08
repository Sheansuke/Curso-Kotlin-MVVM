package com.sheansuke.kotlinmvvm.presentation.screens.signup

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.sheansuke.kotlinmvvm.presentation.screens.signup.components.SignUpContent
import com.sheansuke.kotlinmvvm.presentation.screens.signup.components.SignUpTopBar

@Composable
fun SignUpScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            SignUpTopBar()
        },
        content = {
            SignUpContent()
        },
        bottomBar = {}
    )
}