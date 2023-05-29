package com.sheansuke.kotlinmvvm.domain.use_case.users

import android.net.Uri
import com.sheansuke.kotlinmvvm.domain.repository.UsersRepository
import javax.inject.Inject

class UploadUserImage @Inject constructor(
    private val repository: UsersRepository
) {

    operator suspend fun invoke(imageUri: Uri) = repository.uploadUserImage(imageUri)
}