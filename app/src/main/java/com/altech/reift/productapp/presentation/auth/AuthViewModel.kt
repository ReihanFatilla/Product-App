package com.altech.reift.productapp.presentation.auth

import androidx.lifecycle.ViewModel
import com.altech.reift.core.domain.usecase.auth.AuthUseCase

class AuthViewModel(val authUseCase: AuthUseCase):ViewModel() {
}