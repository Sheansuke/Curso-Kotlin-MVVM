package com.sheansuke.kotlinmvvm.domain.use_case.users

import com.sheansuke.kotlinmvvm.domain.repository.UsersRepository
import javax.inject.Inject

class GetUserById @Inject constructor(
    private val repository: UsersRepository
) {
    operator suspend fun invoke(id: String) = repository.getUserById(id)
}