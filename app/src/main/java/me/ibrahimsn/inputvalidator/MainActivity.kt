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

        val text = "lorem ipsum dolor"

        val validator = InputValidator(arrayOf(
            Validator(editText).notEmpty().min(2).max(3).startsWith("a").endsWith("b"),
            Validator(text).notEmpty().email()
        ))

        validator.setOnValidationListener(object: ValidationListener {
            override fun onValidated(validationResult: InputValidator.ValidationResult) {
                if (!validationResult.isValid)
                    for (error in validationResult.errors)
                        Log.d("###", error)
            }
        })

        button.setOnClickListener {
            validator.validate()
        }
    }
}
