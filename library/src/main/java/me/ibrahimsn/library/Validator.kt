package me.ibrahimsn.library

import android.widget.EditText

class Validator {

    val validated = Validated(0, "", "")

    constructor(id: Int, field: EditText) {
        validated.id = id
        validated.field = field.text.toString().trim()
    }

    constructor(id: Int, field: String) {
        validated.id = id
        validated.field = field.trim()
    }

    fun notEmpty(): Validator {
        if (validated.field == "" && !hasError())
            validated.error = "This field can't be empty!"

        return this
    }

    fun email(): Validator {
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(validated.field).matches() && !hasError())
            validated.error = "This field must be an email!"

        return this
    }

    fun numeric(): Validator {
        if (validated.field.toIntOrNull() == null && !hasError())
            validated.error = "This field must numeric!"

        return this
    }

    fun min(min: Int): Validator {
        if (validated.field.length < min && !hasError())
            validated.error = "This field can have a minimum of $min characters!"

        return this
    }

    fun max(max: Int): Validator {
        if (validated.field.length > max && !hasError())
            validated.error = "This field can have a maximum of $max characters!"

        return this
    }

    fun startsWith(pattern : String): Validator {
        if (!validated.field.startsWith(pattern) && !hasError())
            validated.error = "This field must start with $pattern!"

        return this
    }

    fun endsWith(pattern : String): Validator {
        if (!validated.field.endsWith(pattern) && !hasError())
            validated.error = "This field must end with $pattern!"

        return this
    }

    fun phoneNumber(): Validator {
        if (!validated.field.matches("^[+]?[0-9]{10,13}\$".toRegex()))
            validated.error = "This field must be a phone number!"

        return this
    }

    fun regex(pattern: String): Validator {
        if (!validated.field.matches(pattern.toRegex()))
            validated.error = "Regex error!"

        return this
    }

    private fun hasError(): Boolean {
        return validated.error != ""
    }
}