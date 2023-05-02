package com.sheansuke.kotlinmvvm.domain.use_case.auth

import com.sheansuke.kotlinmvvm.domain.repository.AuthRepository
import javax.inject.Inject

class Logout @Inject constructor(
    private val repository: AuthRepository
) {
    operator fun invoke() = repository.logout()
}