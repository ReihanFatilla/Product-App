package com.altech.reift.core.domain.usecase.auth

import com.altech.reift.core.domain.repository.ProductRepository

class AuthInteractor(val authRepository: ProductRepository): AuthUseCase {
}