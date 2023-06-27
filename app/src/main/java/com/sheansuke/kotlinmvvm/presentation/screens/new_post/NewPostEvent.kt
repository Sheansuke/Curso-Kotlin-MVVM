package com.sheansuke.kotlinmvvm.presentation.screens.new_post

import android.net.Uri

sealed class NewPostEvent {
    data class InputGameName(val gameName: String) : NewPostEvent()
    data class InputGameDescription(val gameDescription: String) : NewPostEvent()
    data class SelectGameCategory(val gameCategory: String) : NewPostEvent()
    data class PickGameImageUri(val gameImageUri: Uri) : NewPostEvent()

    object CreatePost : NewPostEvent()
}
