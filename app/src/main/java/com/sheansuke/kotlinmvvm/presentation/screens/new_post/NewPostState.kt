package com.sheansuke.kotlinmvvm.presentation.screens.new_post

import android.net.Uri

data class NewPostState(
    val name: String? = null,
    val description: String? = null,
    val category: String? = null,
    val imageUri: Uri? = null
)
