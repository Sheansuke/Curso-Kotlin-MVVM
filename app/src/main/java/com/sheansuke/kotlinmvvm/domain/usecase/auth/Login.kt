package com.sheansuke.kotlinmvvm.domain.usecase.auth

import com.sheansuke.kotlinmvvm.domain.repository.AuthRepository
import javax.inject.Inject

class Login @Inject constructor(
   private val repository: AuthRepository
) {

    suspend operator fun invoke(email: String, password: String) =
        repository.login(email, password)
}