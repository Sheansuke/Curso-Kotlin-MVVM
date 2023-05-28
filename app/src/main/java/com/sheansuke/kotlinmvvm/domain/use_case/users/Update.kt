package com.sheansuke.kotlinmvvm.domain.use_case.users

import com.sheansuke.kotlinmvvm.domain.model.User
import com.sheansuke.kotlinmvvm.domain.repository.UsersRepository
import javax.inject.Inject

class Update @Inject constructor(
    private val repository: UsersRepository
) {
    operator suspend fun invoke(user: User) = repository.updatedUser(user)
}