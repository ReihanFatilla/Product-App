package com.altech.reift.core.data.repository

import com.altech.reift.core.data.remote.RemoteDataSource
import com.altech.reift.core.domain.repository.AuthRepository
import com.altech.reift.core.utils.AuthType
import com.google.firebase.auth.AuthResult

class AuthRepositoryImpl(val remoteDataSource: RemoteDataSource): AuthRepository {

    val auth = remoteDataSource.firebaseAuth()
    override suspend fun login(type: AuthType): AuthResult{
        when(type){
            is AuthType.Anonymous -> {
                auth.signInAnonymously()
            }
            is AuthType.Custom -> {
                auth.signInWithEmailAndPassword(type.email, type.password)
            }
            is AuthType.Google -> {

            }
        }
    }

    override suspend fun register(type: AuthType): AuthResult {
        TODO("Not yet implemented")
    }
}