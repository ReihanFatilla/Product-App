package com.altech.reift.productapp.presentation.auth

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.altech.reift.core.domain.model.AuthResult
import com.altech.reift.core.domain.usecase.auth.AuthUseCase
import com.altech.reift.core.utils.AuthType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AuthViewModel(val authUseCase: AuthUseCase) : ViewModel() {

    var authResultLiveData = MutableLiveData<AuthResult>()
    fun login(type: AuthType) {
        CoroutineScope(Dispatchers.IO).launch {
            authResultLiveData.postValue(authUseCase.login(type))
        }
    }

    fun register(type: AuthType) {
        CoroutineScope(Dispatchers.IO).launch {
            authResultLiveData.postValue(authUseCase.register(type))
        }
    }
}