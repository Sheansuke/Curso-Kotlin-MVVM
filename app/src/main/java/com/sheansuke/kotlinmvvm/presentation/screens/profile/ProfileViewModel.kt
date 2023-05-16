package com.sheansuke.kotlinmvvm.presentation.screens.profile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sheansuke.kotlinmvvm.domain.use_case.auth.AuthUseCase
import com.sheansuke.kotlinmvvm.domain.use_case.users.UsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authUseCase: AuthUseCase,
    private val usersUseCase: UsersUseCase
) : ViewModel() {

    init {
        viewModelScope.launch {
            usersUseCase.getUserById(authUseCase.getCurrentUser()!!.uid).collect {
                Log.i("firebaseUser", it.toString())
            }

        }
    }

    fun onEvent(event: ProfileEvent) {
        when (event) {
            is ProfileEvent.Logout -> {
                authUseCase.logout()
            }
        }
    }

}