package com.sheansuke.kotlinmvvm.presentation.screens.new_post

import android.net.Uri
import com.sheansuke.kotlinmvvm.domain.model.Posts

data class NewPostState(
    val name: String? = null,
    val description: String? = null,
    val category: String? = null,
    val imageUri: Uri? = null
) {
    fun toPostModel() = Posts(
        name = name ?: "",
        description = description ?: "",
        category = category ?: "",
    )
}
