package com.sheansuke.kotlinmvvm.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.sheansuke.kotlinmvvm.domain.model.Resource
import com.sheansuke.kotlinmvvm.domain.model.User
import com.sheansuke.kotlinmvvm.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : AuthRepository {

    override val currentUser: FirebaseUser? get() = firebaseAuth.currentUser

    override suspend fun login(email: String, password: String): Flow<Resource<FirebaseUser>> =
        flow {
            emit(Resource.Loading)
            try {
                val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
                emit(Resource.Success(result.user))
            } catch (error: Exception) {
                emit(Resource.Error(null, error))
            }
        }

    override fun logout() {
        firebaseAuth.signOut()
    }

    override suspend fun signup(user: User): Flow<Resource<FirebaseUser>> = flow {
        emit(Resource.Loading)
        try {
            val result =
                firebaseAuth.createUserWithEmailAndPassword(user.email!!, user.password!!).await()
            emit(Resource.Success(result.user))
        } catch (error: Exception) {
            emit(Resource.Error(null, error))
        }
    }

}