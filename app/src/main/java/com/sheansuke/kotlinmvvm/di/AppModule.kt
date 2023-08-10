package com.sheansuke.kotlinmvvm.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.sheansuke.kotlinmvvm.core.Constants
import com.sheansuke.kotlinmvvm.data.repository.AuthRepositoryImpl
import com.sheansuke.kotlinmvvm.data.repository.PostRepositoryImpl
import com.sheansuke.kotlinmvvm.data.repository.UsersRespositoryImpl
import com.sheansuke.kotlinmvvm.domain.repository.AuthRepository
import com.sheansuke.kotlinmvvm.domain.repository.PostRepository
import com.sheansuke.kotlinmvvm.domain.repository.UsersRepository
import com.sheansuke.kotlinmvvm.domain.use_case.auth.AuthUseCase
import com.sheansuke.kotlinmvvm.domain.use_case.auth.GetCurrentUser
import com.sheansuke.kotlinmvvm.domain.use_case.auth.Login
import com.sheansuke.kotlinmvvm.domain.use_case.auth.Logout
import com.sheansuke.kotlinmvvm.domain.use_case.auth.SignUp
import com.sheansuke.kotlinmvvm.domain.use_case.posts.CreatePost
import com.sheansuke.kotlinmvvm.domain.use_case.posts.PostUseCase
import com.sheansuke.kotlinmvvm.domain.use_case.users.Create
import com.sheansuke.kotlinmvvm.domain.use_case.users.GetUserById
import com.sheansuke.kotlinmvvm.domain.use_case.users.Update
import com.sheansuke.kotlinmvvm.domain.use_case.users.UploadUserImage
import com.sheansuke.kotlinmvvm.domain.use_case.users.UsersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
//    CLOUD FIREBASE STORAGE

    @Provides
    @Singleton
    fun provideFirebaseStorage(): FirebaseStorage = FirebaseStorage.getInstance()

    @Provides
    @Singleton
    @Named(Constants.USERS_COLLECTION)
    fun provideUsersStorageRef(storage: FirebaseStorage): StorageReference =
        storage.reference.child(
            Constants.USERS_COLLECTION
        )

    @Provides
    @Singleton
    @Named(Constants.POST_COLLECTION)
    fun providePostsStorageRef(storage: FirebaseStorage): StorageReference =
        storage.reference.child(
            Constants.POST_COLLECTION
        )

    //    FIREBASE
    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideFirebaseFirestore(): FirebaseFirestore = Firebase.firestore

    // FIREBASE COLLECTION
    @Provides
    @Singleton
    @Named(Constants.USERS_COLLECTION)
    fun provideUsersRef(db: FirebaseFirestore): CollectionReference =
        db.collection(Constants.USERS_COLLECTION)

    @Provides
    @Singleton
    @Named(Constants.POST_COLLECTION)
    fun providePostsRef(db: FirebaseFirestore): CollectionReference =
        db.collection(Constants.POST_COLLECTION)

    // REPOSITORY
    @Provides
    @Singleton
    fun provideAuthRepository(impl: AuthRepositoryImpl): AuthRepository = impl

    @Provides
    @Singleton
    fun provideUsersRepository(impl: UsersRespositoryImpl): UsersRepository = impl

    @Provides
    @Singleton
    fun providePostsRepository(impl: PostRepositoryImpl): PostRepository = impl

    // USE CASE
    @Provides
    @Singleton
    fun provideAuthUseCase(repository: AuthRepository) = AuthUseCase(
        getCurrentUser = GetCurrentUser(repository),
        login = Login(repository),
        logout = Logout(repository),
        signUp = SignUp(repository)
    )

    @Provides
    @Singleton
    fun provideUsersUseCase(repository: UsersRepository) = UsersUseCase(
        create = Create(repository),
        getUserById = GetUserById(repository),
        update = Update(repository),
        uploadUserImage = UploadUserImage(repository)
    )

    @Provides
    @Singleton
    fun providePostsUseCase(repository: PostRepository) = PostUseCase(
        create = CreatePost(repository)
    )

}