package com.sheansuke.kotlinmvvm.presentation.screens.new_post

import android.net.Uri

data class NewPostState(
    val gameName: String? = null,
    val gameDescription: String? = null,
    val gameCategory: String? = null,
    val gameImageUri: Uri? = null
)
