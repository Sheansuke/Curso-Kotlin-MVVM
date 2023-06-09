package com.sheansuke.kotlinmvvm.domain.use_case.users

data class UsersUseCase(
    val create: Create,
    val getUserById: GetUserById,
    val update: Update,
    val uploadUserImage: UploadUserImage
)
