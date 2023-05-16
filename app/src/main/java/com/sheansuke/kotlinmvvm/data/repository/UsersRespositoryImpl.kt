package com.sheansuke.kotlinmvvm.data.repository

import com.google.firebase.firestore.CollectionReference
import com.sheansuke.kotlinmvvm.domain.model.Resource
import com.sheansuke.kotlinmvvm.domain.model.User
import com.sheansuke.kotlinmvvm.domain.repository.UsersRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
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

    override suspend fun getUserById(id: String): Flow<User> = callbackFlow {
        val snapshotListener = usersRef.document(id).addSnapshotListener { snapshot, e ->
            val user = snapshot?.toObject(User::class.java) ?: User()
            trySend(user)
        }
        awaitClose {
            snapshotListener.remove()
        }

    }
}