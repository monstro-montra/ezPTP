package com.javierlabs.ezptp.data

data class SafetyPlanOption(
    var title: String,
    var variables: List<String>,
    val inputType: InputType
)

enum class InputType {
    CHECKBOX,
    RADIOBUTTON
}
