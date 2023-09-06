package com.altech.reift.core.domain.repository

import com.altech.reift.core.domain.model.AuthResult
import com.altech.reift.core.utils.AuthType

interface AuthRepository {
    suspend fun login(type: AuthType): AuthResult
    suspend fun register(type: AuthType): AuthResult
    fun isAnonymous(): Boolean
}