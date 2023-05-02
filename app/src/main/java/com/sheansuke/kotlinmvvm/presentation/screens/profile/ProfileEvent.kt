package com.sheansuke.kotlinmvvm.presentation.screens.profile

sealed class ProfileEvent {
    object Logout : ProfileEvent()
}
