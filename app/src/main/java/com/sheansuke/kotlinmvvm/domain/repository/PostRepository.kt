package com.sheansuke.kotlinmvvm.domain.repository

import android.net.Uri
import com.sheansuke.kotlinmvvm.domain.model.Post
import com.sheansuke.kotlinmvvm.domain.model.Resource
import kotlinx.coroutines.flow.Flow

interface PostRepository {
    suspend fun create(newPost: Post, imageUri: Uri? = null): Flow<Resource<Post>>
}