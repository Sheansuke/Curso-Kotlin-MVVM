package com.sheansuke.kotlinmvvm.presentation.screens.profile.components

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.sheansuke.kotlinmvvm.presentation.components.DefaultButton
import com.sheansuke.kotlinmvvm.presentation.navigation.AppScreen
import com.sheansuke.kotlinmvvm.presentation.screens.profile.ProfileEvent
import com.sheansuke.kotlinmvvm.presentation.screens.profile.ProfileViewModel

@Composable
fun ProfileContent(
    navController: NavHostController,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    DefaultButton(text = "Cerrar Sesion", onClick = {
        viewModel.onEvent(ProfileEvent.Logout)
        navController.navigate(AppScreen.Login.routeName) {
            popUpTo(AppScreen.Profile.routeName) { inclusive = true }
        }
    })
}