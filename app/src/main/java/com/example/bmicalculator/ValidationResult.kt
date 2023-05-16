package com.example.bmicalculator

sealed class ValidationResult {
    object Valid : ValidationResult()
    data class Invalid(val errorMessage: String) : ValidationResult()
}

