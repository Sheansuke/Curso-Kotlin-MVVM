package com.sheansuke.kotlinmvvm.domain.repository

import com.google.firebase.auth.FirebaseUser
import com.sheansuke.kotlinmvvm.domain.model.Resource
import com.sheansuke.kotlinmvvm.domain.model.User

interface AuthRepository {
    val currentUser: FirebaseUser?

    suspend fun login(email: String, password: String): Resource<FirebaseUser>
    fun logout()
    suspend fun signup(user: User): Resource<FirebaseUser>
}