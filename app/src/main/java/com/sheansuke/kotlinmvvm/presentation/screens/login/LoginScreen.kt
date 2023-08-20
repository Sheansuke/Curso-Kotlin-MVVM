package com.sheansuke.kotlinmvvm.presentation.screens.login

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.sheansuke.kotlinmvvm.presentation.components.LoadingIndicator
import com.sheansuke.kotlinmvvm.presentation.navigation.AuthenticationNavGraphRoutes
import com.sheansuke.kotlinmvvm.presentation.navigation.RootGraph
import com.sheansuke.kotlinmvvm.presentation.screens.login.components.LoginBottomBar
import com.sheansuke.kotlinmvvm.presentation.screens.login.components.LoginContent


@Composable
fun LoginScreen(
    navController: NavHostController, viewModel: LoginViewModel = hiltViewModel()
) {
    val stateFlow = viewModel.eventFlow.collectAsStateWithLifecycle(null)

    Scaffold(topBar = {}, content = {
        LoginContent()

        LoadingIndicator(
            state = stateFlow, successMessage = "User Signin", errorMessage = "An error ocurred",
            onSuccess = {
                navController.navigate(RootGraph.HOME) {
                    popUpTo(AuthenticationNavGraphRoutes.Login.routeName) { inclusive = true }
                }

            },
        )
    }, bottomBar = {
        LoginBottomBar(navController)
    })
}

