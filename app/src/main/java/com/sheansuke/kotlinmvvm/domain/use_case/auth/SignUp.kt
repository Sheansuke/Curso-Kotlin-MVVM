package com.sheansuke.kotlinmvvm.domain.use_case.auth

import com.sheansuke.kotlinmvvm.domain.model.User
import com.sheansuke.kotlinmvvm.domain.repository.AuthRepository
import javax.inject.Inject

class SignUp @Inject constructor(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(user: User) = authRepository.signup(user)
}