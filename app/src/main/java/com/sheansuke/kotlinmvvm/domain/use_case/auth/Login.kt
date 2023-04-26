package com.sheansuke.kotlinmvvm.domain.use_case.auth

import com.sheansuke.kotlinmvvm.domain.repository.AuthRepository
import javax.inject.Inject

class Login @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String) = repository.login(email, password)
}