package com.javierlabs.ezptp.auth

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginViewModel: ViewModel(){

    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()
}

class LoginState