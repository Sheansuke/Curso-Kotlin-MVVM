package com.sheansuke.kotlinmvvm.di

import com.google.firebase.auth.FirebaseAuth
import com.sheansuke.kotlinmvvm.data.repository.AuthRepositoryImpl
import com.sheansuke.kotlinmvvm.domain.repository.AuthRepository
import com.sheansuke.kotlinmvvm.domain.use_case.auth.AuthUseCase

import com.sheansuke.kotlinmvvm.domain.use_case.auth.GetCurrentUser
import com.sheansuke.kotlinmvvm.domain.use_case.auth.Login
import com.sheansuke.kotlinmvvm.domain.use_case.auth.Logout
import com.sheansuke.kotlinmvvm.domain.use_case.auth.SignUp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    // REPOSITORY
    @Provides
    fun provideAuthRepository(impl: AuthRepositoryImpl): AuthRepository = impl

    // USE CASE
    @Provides
    fun provideAuthUseCase(repository: AuthRepository) = AuthUseCase(
        getCurrentUser = GetCurrentUser(repository),
        login = Login(repository),
        logout = Logout(repository),
        signUp = SignUp(repository)
    )

}