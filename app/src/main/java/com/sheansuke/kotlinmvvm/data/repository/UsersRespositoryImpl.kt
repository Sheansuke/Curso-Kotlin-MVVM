package com.sheansuke.kotlinmvvm.data.repository

import android.net.Uri
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.storage.StorageReference
import com.sheansuke.kotlinmvvm.domain.model.Resource
import com.sheansuke.kotlinmvvm.domain.model.User
import com.sheansuke.kotlinmvvm.domain.repository.UsersRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import java.io.File
import javax.inject.Inject

class UsersRespositoryImpl @Inject constructor(
    private val usersRef: CollectionReference,
    private val usersStorageRef: StorageReference
) : UsersRepository {
    override suspend fun create(user: User): Resource<Boolean> {
        return try {
            usersRef.add(user).await()
            Resource.Success(true)
        } catch (error: Exception) {
            Resource.Error(null, error)
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

    override suspend fun updatedUser(user: User): Flow<Resource<Boolean>> = flow {
        try {
            usersRef.document(user.id!!).set(user).await()
            emit(Resource.Success(true))
        } catch (error: Exception) {
            emit(Resource.Error(null, error))
        }
    }

    override suspend fun uploadUserImage(imageUri: Uri): Flow<Resource<String>> = flow {
        try {
            val filename = File(imageUri.path).name
            val imageRef = usersStorageRef.child("users/images/profile/${filename}")
            imageRef.putFile(imageUri).await()
            val imageUrl = imageRef.downloadUrl.await()
            emit(Resource.Success(imageUrl.toString()))
        } catch (error: Exception) {
            emit(Resource.Error(null, error))
        }
    }
}