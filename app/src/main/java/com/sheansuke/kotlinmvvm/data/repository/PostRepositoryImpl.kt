package com.sheansuke.kotlinmvvm.data.repository

import android.net.Uri
import android.util.Log
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.storage.StorageReference
import com.sheansuke.kotlinmvvm.core.Constants
import com.sheansuke.kotlinmvvm.domain.model.Posts
import com.sheansuke.kotlinmvvm.domain.model.Resource
import com.sheansuke.kotlinmvvm.domain.repository.PostRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import java.io.File
import javax.inject.Inject
import javax.inject.Named

class PostRepositoryImpl @Inject constructor(
    @Named(Constants.POST_COLLECTION) private val postsRef: CollectionReference,
    @Named(Constants.POST_COLLECTION) private val postsStorageRef: StorageReference
) : PostRepository {
    override suspend fun create(newPost: Posts, imageUri: Uri?): Flow<Resource<Posts>> =
        flow {
            Log.i("newPost", newPost.toString())
            emit(Resource.Loading)
            try {

                var imageUrl: String? = ""
                if (imageUri != null) {
                    val fileNamed = File(imageUri.path).name
                    val imageRef = postsStorageRef.storage.reference.child("/images/${fileNamed}")
                    val uploadImage = imageRef.putFile(imageUri).await()
                    imageUrl = uploadImage.storage.downloadUrl.await().toString()
                }

                val newCreatedPost = postsRef.add(
                    newPost.copy(
                        imageUrl = imageUrl.toString()
                    )
                ).await()
                emit(
                    Resource.Success(
                        newCreatedPost.get().await().toObject(Posts::class.java)
                    )
                )
            } catch (error: Exception) {
                emit(
                    Resource.Error(
                        data = null, exception = error
                    )
                )
            }
        }
}