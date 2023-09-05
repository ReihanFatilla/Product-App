package com.altech.reift.core.domain.usecase.auth

import com.altech.reift.core.domain.model.AuthResult
import com.altech.reift.core.domain.repository.AuthRepository
import com.altech.reift.core.domain.repository.ProductRepository
import com.altech.reift.core.utils.AuthType

class AuthInteractor(val authRepository: AuthRepository): AuthUseCase {
    override suspend fun login(type: AuthType): AuthResult{
        return authRepository.login(type)
    }

    override suspend fun register(type: AuthType): AuthResult{
        return authRepository.register(type)
    }
}