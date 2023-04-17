package com.sheansuke.kotlinmvvm.domain.usecase.auth

import com.sheansuke.kotlinmvvm.domain.repository.AuthRepository
import javax.inject.Inject

class GetCurrentUser @Inject constructor(
   private val repository: AuthRepository
) {

    operator fun invoke() = repository.currentUser
}