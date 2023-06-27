package com.sheansuke.kotlinmvvm.presentation.screens.home.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.sheansuke.kotlinmvvm.presentation.navigation.HomeScreenBottomBar

@Composable
fun HomeBottomBar(navController: NavHostController) {

    var isBottomBarVisible by remember {
        mutableStateOf(false)
    }

    val navBackStackNavigation by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackNavigation?.destination

    val screens = listOf(
        HomeScreenBottomBar.Posts,
        HomeScreenBottomBar.MyPosts,
        HomeScreenBottomBar.Profile,
    )

    LaunchedEffect(currentDestination?.route) {
        val isRouteInBottomBar = screens.find { it.route.equals(currentDestination?.route) }
        isBottomBarVisible = isRouteInBottomBar?.route != null
    }

    AnimatedVisibility(
        visible = isBottomBarVisible, enter = expandVertically(
            expandFrom = Alignment.Top
        ), exit = slideOutVertically(
            targetOffsetY = { it }
        )
    ) {
        BottomNavigation() {
            screens.forEach {
                BottomNavigationItem(
                    label = {
                        Text(text = it.title)
                    },
                    selected = it.route == currentDestination?.route,
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
}