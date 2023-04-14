package com.javierlabs.ezptp.viewmodel

import androidx.lifecycle.ViewModel
import com.javierlabs.ezptp.data.SafetyPlanOption

class SharedViewModel : ViewModel() {
    val selectedOptions = mutableListOf<SafetyPlanOption>()
}