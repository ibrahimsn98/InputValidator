package me.ibrahimsn.library

class InputValidator(private val validators: Array<Validator>) {

    private val errors = mutableListOf<String>()
    private var validationListener: ValidationListener? = null

    fun validate(): ValidationResult {
        val result = ValidationResult(true, mutableListOf())
        errors.clear()

        for (validator in validators) {
            val error = validator.validated.error
            if (error != "") {
                result.isValid = false
                result.errors.add(error)
            }
        }

        if (validationListener != null)
            validationListener!!.onValidated(result)

        return result
    }

    fun setOnValidationListener(validationListener: ValidationListener) {
        this.validationListener = validationListener
    }

    data class ValidationResult(var isValid: Boolean, val errors: MutableList<String>)
}