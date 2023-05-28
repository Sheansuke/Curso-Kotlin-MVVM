package com.sheansuke.kotlinmvvm.domain.repository

import com.sheansuke.kotlinmvvm.domain.model.Resource
import com.sheansuke.kotlinmvvm.domain.model.User
import kotlinx.coroutines.flow.Flow


interface UsersRepository {
    suspend fun create(user: User): Resource<Boolean>
    suspend fun getUserById(id: String): Flow<User>
    suspend fun updatedUser(user: User): Flow<Resource<Boolean>>
}