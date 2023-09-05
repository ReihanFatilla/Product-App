package com.altech.reift.core.domain.usecase.auth

import com.altech.reift.core.domain.model.AuthResult
import com.altech.reift.core.utils.AuthType

interface AuthUseCase {
    suspend fun login(type: AuthType): AuthResult
    suspend fun register(type: AuthType): AuthResult
}