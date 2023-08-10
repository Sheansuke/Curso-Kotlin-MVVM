package com.sheansuke.kotlinmvvm.domain.repository

import android.net.Uri
import com.sheansuke.kotlinmvvm.domain.model.Posts
import com.sheansuke.kotlinmvvm.domain.model.Resource
import kotlinx.coroutines.flow.Flow

interface PostRepository {
    suspend fun create(newPost: Posts, imageUri: Uri? = null): Flow<Resource<Posts>>
}