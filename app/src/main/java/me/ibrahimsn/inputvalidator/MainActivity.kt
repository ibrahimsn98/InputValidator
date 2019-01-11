package me.ibrahimsn.inputvalidator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import me.ibrahimsn.library.InputValidator
import me.ibrahimsn.library.ValidationListener
import me.ibrahimsn.library.Validator

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText = findViewById<EditText>(R.id.editText)
        val button = findViewById<Button>(R.id.button)

        val password = "123456"
        val confirmPassword = "123456"
        val text = "lorem ipsum dolor"

        val validator = InputValidator(arrayOf(
            Validator(0, password).notEmpty(),
            Validator(1, confirmPassword).notEmpty(),
            Validator(2, text).notEmpty().min(3).max(50).startsWith("l").endsWith("r"),
            Validator(3, editText).notEmpty().email()
        )).completes(0,1)

        val results = validator.validate()

        //OR

        validator.setOnValidationListener(object: ValidationListener {
            override fun onValidated(validationResult: InputValidator.ValidationResult) {
                if (!validationResult.isValid)
                    for (key in validationResult.errors.keys)
                        Log.d("###", "[$key]: ${validationResult.errors[key]}")
            }
        })

        button.setOnClickListener { validator.validate() }
    }
}
