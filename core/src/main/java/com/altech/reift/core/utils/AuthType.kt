package com.altech.reift.core.utils

sealed class AuthType {
    object Google : AuthType()
    object Anonymous : AuthType()
    data class Custom(val email: String, val password: String): AuthType()
}