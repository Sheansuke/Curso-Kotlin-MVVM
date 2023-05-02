package com.sheansuke.kotlinmvvm.presentation.screens.profile

import androidx.lifecycle.ViewModel
import com.sheansuke.kotlinmvvm.domain.use_case.auth.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authUseCase: AuthUseCase
) : ViewModel() {


    fun onEvent(event: ProfileEvent) {
        when (event) {
            is ProfileEvent.Logout -> {
                authUseCase.logout()
            }
        }
    }

}