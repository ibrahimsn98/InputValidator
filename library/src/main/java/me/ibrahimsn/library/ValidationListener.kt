package me.ibrahimsn.library

interface ValidationListener {
    fun onValidated(validationResult: InputValidator.ValidationResult)
}