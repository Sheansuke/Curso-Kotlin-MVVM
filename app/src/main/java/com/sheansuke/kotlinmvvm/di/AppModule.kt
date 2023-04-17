package com.sheansuke.kotlinmvvm.di

import com.google.firebase.auth.FirebaseAuth
import com.sheansuke.kotlinmvvm.data.repository.AuthRepositoryImpl
import com.sheansuke.kotlinmvvm.domain.repository.AuthRepository
import com.sheansuke.kotlinmvvm.domain.usecase.auth.AuthUseCases
import com.sheansuke.kotlinmvvm.domain.usecase.auth.GetCurrentUser
import com.sheansuke.kotlinmvvm.domain.usecase.auth.Login
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun provideAuthRepository(impl: AuthRepositoryImpl): AuthRepository = impl

    @Provides
    fun provideAuthUseCases(repository: AuthRepository) = AuthUseCases(
        getCurrentUser = GetCurrentUser(repository),
        login = Login(repository)
    )
}