package me.ibrahimsn.library

import java.lang.Exception

class InputValidator(private val validators: Array<Validator>) {

    private var validationListener: ValidationListener? = null

    fun validate(): ValidationResult {
        val result = ValidationResult(true, mutableMapOf())

        for (validator in validators) {
            val isValid = validator.validated.valid

            if (!isValid) {
                result.isValid = false
                result.errors[validator.validated.id] = result.isValid
            }
        }

        if (validationListener != null)
            validationListener!!.onValidated(result)

        return result
    }

    fun completes(id0: Int, id1: Int): InputValidator {
        val val0 = getFieldById(id0)
        val val1 = getFieldById(id1)

        if (val0 == null || val1 == null)
            throw Exception("Completed field id's can't be null!")

        if (val0.field != val1.field)
            val1.valid = false

        return this
    }

    private fun getFieldById(id: Int): Validated? {
        for (validator in validators)
            if (validator.validated.id == id)
                return validator.validated

        return null
    }

    fun setOnValidationListener(validationListener: ValidationListener) {
        this.validationListener = validationListener
    }

    data class ValidationResult(var isValid: Boolean, val errors: MutableMap<Int, Boolean>)
}