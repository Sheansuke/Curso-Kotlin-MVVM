package com.sheansuke.kotlinmvvm.presentation.screens.home

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.sheansuke.kotlinmvvm.presentation.navigation.HomeScreenNavGraph
import com.sheansuke.kotlinmvvm.presentation.screens.home.components.HomeBottomBar

@Composable
fun HomeScreen(navController: NavHostController = rememberNavController()) {
    Scaffold(
        topBar = {},
        bottomBar = {
            HomeBottomBar(navController)
        }
    ) {
        HomeScreenNavGraph(navController)
    }
}
