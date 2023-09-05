package com.altech.reift.core.domain.usecase.auth

import com.altech.reift.core.utils.AuthType

interface AuthUseCase {
    fun login(type: AuthType)
    fun register(type: AuthType)
}