package com.sheansuke.kotlinmvvm.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.sheansuke.kotlinmvvm.core.Constants
import com.sheansuke.kotlinmvvm.data.repository.AuthRepositoryImpl
import com.sheansuke.kotlinmvvm.data.repository.UsersRespositoryImpl
import com.sheansuke.kotlinmvvm.domain.repository.AuthRepository
import com.sheansuke.kotlinmvvm.domain.repository.UsersRepository
import com.sheansuke.kotlinmvvm.domain.use_case.auth.AuthUseCase

import com.sheansuke.kotlinmvvm.domain.use_case.auth.GetCurrentUser
import com.sheansuke.kotlinmvvm.domain.use_case.auth.Login
import com.sheansuke.kotlinmvvm.domain.use_case.auth.Logout
import com.sheansuke.kotlinmvvm.domain.use_case.auth.SignUp
import com.sheansuke.kotlinmvvm.domain.use_case.users.Create
import com.sheansuke.kotlinmvvm.domain.use_case.users.GetUserById
import com.sheansuke.kotlinmvvm.domain.use_case.users.UsersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    //    FIREBASE
    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun provideFirebaseFirestore(): FirebaseFirestore = Firebase.firestore

    @Provides
    fun provideUsersRef(db: FirebaseFirestore): CollectionReference =
        db.collection(Constants.USERS_COLLECTION)

    // REPOSITORY
    @Provides
    fun provideAuthRepository(impl: AuthRepositoryImpl): AuthRepository = impl

    @Provides
    fun provideUsersRepository(impl: UsersRespositoryImpl): UsersRepository = impl

    // USE CASE
    @Provides
    fun provideAuthUseCase(repository: AuthRepository) = AuthUseCase(
        getCurrentUser = GetCurrentUser(repository),
        login = Login(repository),
        logout = Logout(repository),
        signUp = SignUp(repository)
    )

    @Provides
    fun provideUsersUseCase(repository: UsersRepository) = UsersUseCase(
        create = Create(repository),
        getUserById = GetUserById(repository)
    )

}