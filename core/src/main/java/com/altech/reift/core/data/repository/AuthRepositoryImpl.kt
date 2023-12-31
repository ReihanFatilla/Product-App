package com.altech.reift.core.data.repository

import android.content.Intent
import android.widget.Toast
import com.altech.reift.core.data.remote.RemoteDataSource
import com.altech.reift.core.domain.model.AuthResult
import com.altech.reift.core.domain.repository.AuthRepository
import com.altech.reift.core.utils.AuthType
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


class AuthRepositoryImpl(val remoteDataSource: RemoteDataSource): AuthRepository {

    val auth = remoteDataSource.firebaseAuth
    override suspend fun login(type: AuthType): AuthResult {
        return when(type){
            is AuthType.Anonymous -> {
                suspendCoroutine { continuation ->
                    auth.signInAnonymously()
                        .addOnSuccessListener {
                            continuation.resume(AuthResult(true, "You're a Guest"))
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
                suspendCoroutine {continuation ->
                    auth.signInWithCredential(type.credential)
                        .addOnCompleteListener{
                            continuation.resume(AuthResult(true, "Success Sign In with Google Account"))
                        }
                        .addOnFailureListener {exception ->
                            continuation.resume(AuthResult(false, exception.message))
                        }
                    AuthResult(success = false, message = null)
                }
            }
        }
    }

    override suspend fun register(type: AuthType): AuthResult {
        return when(type){
            is AuthType.Anonymous -> {
                suspendCoroutine { continuation ->
                    auth.signInAnonymously()
                        .addOnSuccessListener {
                            continuation.resume(AuthResult(true, "You're a Guest"))
                        }
                        .addOnFailureListener { exception ->
                            continuation.resume(AuthResult(false, exception.message))
                        }
                }
            }
            is AuthType.Custom -> {
                suspendCoroutine { continuation ->
                    auth.createUserWithEmailAndPassword(type.email, type.password)
                        .addOnSuccessListener {
                            continuation.resume(AuthResult(true, "Success Signed Up"))
                        }
                        .addOnFailureListener { exception ->
                            continuation.resume(AuthResult(false, exception.message))
                        }
                }
            }
            is AuthType.Google -> {
                suspendCoroutine {continuation ->
                    auth.signInWithCredential(type.credential)
                        .addOnCompleteListener{
                            continuation.resume(AuthResult(true, "Success Sign In with Google Account"))
                        }
                        .addOnFailureListener {exception ->
                            continuation.resume(AuthResult(false, exception.message))
                        }
                AuthResult(success = false, message = null)
                }
            }
        }
    }

    override fun isAnonymous(): Boolean {
        return remoteDataSource.firebaseAuth.currentUser?.isAnonymous ?: true
    }
}