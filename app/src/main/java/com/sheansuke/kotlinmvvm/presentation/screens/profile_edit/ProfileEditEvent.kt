package com.sheansuke.kotlinmvvm.presentation.screens.profile_edit

import android.net.Uri

sealed class ProfileEditEvent {
    data class InputUserName (val username: String): ProfileEditEvent()
    data class PickImage (val imageUri: Uri): ProfileEditEvent()
    object UpdateUser: ProfileEditEvent()

}
