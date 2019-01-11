package me.ibrahimsn.library

import android.widget.EditText

class Validator {

    val validated = Validated("", "")

    constructor(field: EditText) {
        validated.field = field.text.toString().trim()
    }

    constructor(field: String) {
        validated.field = field.trim()
    }

    fun notEmpty(): Validator {
        if (validated.field == "" && !hasError())
            validated.error = "Not be empty!"

        return this
    }

    fun email(): Validator {
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(validated.field).matches() && !hasError())
            validated.error = "Must be email!"

        return this
    }

    fun numeric(): Validator {
        if (validated.field.toIntOrNull() == null && !hasError())
            validated.error = "Must be numeric!"

        return this
    }

    fun min(min: Int): Validator {
        if (validated.field.length < min && !hasError())
            validated.error = "cant be lower than $min"

        return this
    }

    fun max(max: Int): Validator {
        if (validated.field.length > max && !hasError())
            validated.error = "cant be bigger than $max"

        return this
    }

    fun startsWith(pattern : String): Validator {
        if (!validated.field.startsWith(pattern) && !hasError())
            validated.error = "must start with $pattern"

        return this
    }

    fun endsWith(pattern : String): Validator {
        if (!validated.field.endsWith(pattern) && !hasError())
            validated.error = "must end with $pattern"

        return this
    }

    private fun hasError(): Boolean {
        return validated.error != ""
    }
}