package com.sheansuke.kotlinmvvm.data.repository

import com.google.firebase.firestore.CollectionReference
import com.sheansuke.kotlinmvvm.domain.model.Resource
import com.sheansuke.kotlinmvvm.domain.model.User
import com.sheansuke.kotlinmvvm.domain.repository.UsersRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UsersRespositoryImpl @Inject constructor(
    private val usersRef: CollectionReference
) : UsersRepository {
    override suspend fun create(user: User): Resource<Boolean> {
        return try {
            usersRef.add(user).await()
            Resource.Success(true)
        } catch (error: Exception) {
            Resource.Error(error)
        }
    }
}