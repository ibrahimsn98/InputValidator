package me.ibrahimsn.library

import android.widget.EditText

class Validator {

    val validated = Validated(0, "", true)

    constructor(id: Int, field: EditText) {
        validated.id = id
        validated.field = field.text.toString().trim()
    }

    constructor(id: Int, field: String) {
        validated.id = id
        validated.field = field.trim()
    }

    fun notEmpty(): Validator {
        if (validated.field == "")
            validated.valid = false

        return this
    }

    fun email(): Validator {
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(validated.field).matches())
            validated.valid = false

        return this
    }

    fun numeric(): Validator {
        if (validated.field.toIntOrNull() == null)
            validated.valid = false

        return this
    }

    fun min(min: Int): Validator {
        if (validated.field.length < min)
            validated.valid = false

        return this
    }

    fun max(max: Int): Validator {
        if (validated.field.length > max)
            validated.valid = false

        return this
    }

    fun startsWith(pattern : String): Validator {
        if (!validated.field.startsWith(pattern))
            validated.valid = false

        return this
    }

    fun endsWith(pattern : String): Validator {
        if (!validated.field.endsWith(pattern))
            validated.valid = false

        return this
    }

    fun phoneNumber(): Validator {
        if (!validated.field.matches("^[+]?[0-9]{10,14}\$".toRegex()))
            validated.valid = false

        return this
    }

    fun regex(pattern: String): Validator {
        if (!validated.field.matches(pattern.toRegex()))
            validated.valid = false

        return this
    }
}