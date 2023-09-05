package com.altech.reift.core.domain.usecase.auth

import com.altech.reift.core.domain.repository.ProductRepository
import com.altech.reift.core.utils.AuthType

class AuthInteractor(val authRepository: ProductRepository): AuthUseCase {
    override fun login(type: AuthType) {
        TODO("Not yet implemented")
    }

    override fun register(type: AuthType) {
        TODO("Not yet implemented")
    }
}