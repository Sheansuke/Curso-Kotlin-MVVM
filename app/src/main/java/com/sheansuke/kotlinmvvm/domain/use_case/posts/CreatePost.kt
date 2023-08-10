package com.sheansuke.kotlinmvvm.domain.use_case.posts

import android.net.Uri
import com.sheansuke.kotlinmvvm.domain.model.Posts
import com.sheansuke.kotlinmvvm.domain.repository.PostRepository
import javax.inject.Inject

class CreatePost @Inject constructor(
    private val repository: PostRepository
) {
    operator suspend fun invoke(newPost: Posts, imageUri: Uri? = null) =
        repository.create(newPost, imageUri)

}