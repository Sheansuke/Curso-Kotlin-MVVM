package com.sheansuke.kotlinmvvm.domain.repository

import com.sheansuke.kotlinmvvm.domain.model.Resource
import com.sheansuke.kotlinmvvm.domain.model.User

interface UsersRepository {
    suspend fun create(user: User): Resource<Boolean>
}