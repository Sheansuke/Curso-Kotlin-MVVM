package com.sheansuke.kotlinmvvm.domain.use_case.auth

import com.google.firebase.auth.FirebaseUser
import com.sheansuke.kotlinmvvm.domain.repository.AuthRepository
import javax.inject.Inject

class GetCurrentUser @Inject constructor(
    private val repository: AuthRepository
) {

    operator fun invoke(): FirebaseUser? = repository.currentUser

}