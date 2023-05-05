package com.javierlabs.ezptp.auth

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class RegisterViewModel: ViewModel(){

    private val _state = MutableStateFlow(RegisterState())
    val state = _state.asStateFlow()
}

class RegisterState