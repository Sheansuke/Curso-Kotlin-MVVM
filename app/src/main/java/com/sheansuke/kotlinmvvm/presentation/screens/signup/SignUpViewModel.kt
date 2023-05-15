package com.sheansuke.kotlinmvvm.presentation.screens.signup

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.sheansuke.kotlinmvvm.domain.model.Resource
import com.sheansuke.kotlinmvvm.domain.model.User
import com.sheansuke.kotlinmvvm.domain.use_case.auth.AuthUseCase
import com.sheansuke.kotlinmvvm.domain.use_case.users.UsersUseCase
import com.sheansuke.kotlinmvvm.presentation.utils.validateConfirmPassword
import com.sheansuke.kotlinmvvm.presentation.utils.validateEmail
import com.sheansuke.kotlinmvvm.presentation.utils.validatePassword
import com.sheansuke.kotlinmvvm.presentation.utils.validateUsername
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authUseCase: AuthUseCase,
    private val usersUseCase: UsersUseCase
) : ViewModel() {

    private val _signUpState: MutableState<SignUpState> = mutableStateOf(SignUpState(""))
    var signUpState: State<SignUpState> = _signUpState

    private val _signUpFlow = MutableStateFlow<Resource<FirebaseUser>?>(null)
    val signUpFlow: StateFlow<Resource<FirebaseUser>?> = _signUpFlow

    val newUser = User("","","","")

    fun onSignUp() = viewModelScope.launch {
        newUser.id = authUseCase.getCurrentUser()?.uid
        usersUseCase.create(newUser)
    }

    // EVENTS -------------------------------------------------------
    fun onEvent(event: SignUpEvent) {
        when (event) {
            is SignUpEvent.InputUserName -> {
                _signUpState.value = signUpState.value.copy(
                    username = event.username,
                    isValidUserName = validateUsername(event.username)
                )
                validateForm()
            }

            is SignUpEvent.InputEmail -> {
                _signUpState.value = signUpState.value.copy(
                    email = event.email,
                    isValidEmail = validateEmail(event.email)
                )
                validateForm()
            }

            is SignUpEvent.InputPassword -> {
                _signUpState.value = signUpState.value.copy(
                    password = event.password,
                    isValidPassword = validatePassword(event.password)
                )
                validateForm()
            }

            is SignUpEvent.InputConfirmPassword -> {
                _signUpState.value = signUpState.value.copy(
                    confirmPassword = event.confirmPassword,
                    isValidConfirmPassword = validateConfirmPassword(
                        _signUpState.value.password,
                        event.confirmPassword
                    )
                )
                validateForm()
            }

            is SignUpEvent.SignUp -> {
                _signUpFlow.value = Resource.Loading
                newUser.username = _signUpState.value.username
                newUser.email = _signUpState.value.email
                newUser.password = _signUpState.value.password
                viewModelScope.launch {
                    val result = authUseCase.signUp(newUser)
                    _signUpFlow.value = result
                }
            }

        }
    }

    fun validateForm() {
        if (_signUpState.value.isValidEmail == true &&
            _signUpState.value.isValidPassword == true &&
            _signUpState.value.isValidUserName == true &&
            _signUpState.value.isValidConfirmPassword == true
        ) {
            _signUpState.value = signUpState.value.copy(
                isValidForm = true
            )
        } else {
            _signUpState.value = signUpState.value.copy(
                isValidForm = false
            )
        }
    }


}