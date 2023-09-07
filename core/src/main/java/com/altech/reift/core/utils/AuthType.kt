package com.altech.reift.core.utils

import com.google.firebase.auth.AuthCredential

sealed class AuthType {
    data class Google(val credential: AuthCredential): AuthType()
    object Anonymous : AuthType()
    data class Custom(val email: String, val password: String): AuthType()
}