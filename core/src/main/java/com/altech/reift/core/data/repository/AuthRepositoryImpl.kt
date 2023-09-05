package com.altech.reift.core.data.repository

import com.altech.reift.core.data.remote.RemoteDataSource
import com.altech.reift.core.domain.model.AuthResult
import com.altech.reift.core.domain.repository.AuthRepository
import com.altech.reift.core.utils.AuthType
import kotlinx.coroutines.tasks.await
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


class AuthRepositoryImpl(val remoteDataSource: RemoteDataSource): AuthRepository {

    val auth = remoteDataSource.firebaseAuth()
    override suspend fun login(type: AuthType): AuthResult {
        return when(type){
            is AuthType.Anonymous -> {
                suspendCoroutine { continuation ->
                    auth.signInAnonymously()
                        .addOnSuccessListener {
                            continuation.resume(AuthResult(true, "Success Signing In"))
                        }
                        .addOnFailureListener { exception ->
                            continuation.resume(AuthResult(false, exception.message))
                        }
                }
            }
            is AuthType.Custom -> {
                suspendCoroutine { continuation ->
                    auth.signInWithEmailAndPassword(type.email, type.password)
                        .addOnSuccessListener {
                            continuation.resume(AuthResult(true, "Success Signing In"))
                        }
                        .addOnFailureListener { exception ->
                            continuation.resume(AuthResult(false, exception.message))
                        }
                }
            }
            is AuthType.Google -> {
                AuthResult(success = false, message = null)
            }
        }
    }

    override suspend fun register(type: AuthType): AuthResult {
        TODO("Not yet implemented")
    }
}