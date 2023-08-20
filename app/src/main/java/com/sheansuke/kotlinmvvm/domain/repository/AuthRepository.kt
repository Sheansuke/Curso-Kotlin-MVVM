package com.sheansuke.kotlinmvvm.domain.repository

import com.google.firebase.auth.FirebaseUser
import com.sheansuke.kotlinmvvm.domain.model.Resource
import com.sheansuke.kotlinmvvm.domain.model.User
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    val currentUser: FirebaseUser?

    suspend fun login(email: String, password: String): Flow<Resource<FirebaseUser>>
    fun logout()
    suspend fun signup(user: User): Flow<Resource<FirebaseUser>>
}