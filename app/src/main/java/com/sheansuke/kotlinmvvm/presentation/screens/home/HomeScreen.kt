package com.sheansuke.kotlinmvvm.presentation.screens.home

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun HomeScreen(navController: NavHostController = rememberNavController()) {
    Scaffold(
        topBar = {},
        content = {
            Text(text = "HomeScreen")
        },
        bottomBar = {}
    )

}