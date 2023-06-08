package com.sheansuke.kotlinmvvm.presentation.screens.home

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.sheansuke.kotlinmvvm.presentation.navigation.HomeScreenBottomBar
import com.sheansuke.kotlinmvvm.presentation.navigation.HomeScreenNavGraph

@Composable
fun HomeScreen(navController: NavHostController = rememberNavController()) {
    Scaffold(
        topBar = {},
        bottomBar = {
            BottomBar(navController)
        }
    ) {
        HomeScreenNavGraph(navController)
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        HomeScreenBottomBar.Posts,
        HomeScreenBottomBar.MyPosts,
        HomeScreenBottomBar.Profile,
    )

    BottomNavigation {
        screens.forEach {
            BottomNavigationItem(
                label = {
                        Text(text = it.title)
                },
                selected = false,
                icon = { Icon(imageVector = it.icon, contentDescription = it.route) },
                onClick = {
                          navController.navigate(it.route) {
                              popUpTo(navController.graph.findStartDestination().id)
                              launchSingleTop = true
                          }
                },
            )
        }
    }

}