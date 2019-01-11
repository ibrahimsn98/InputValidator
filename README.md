
## InputValidator
A Lightweight input validation library for Android

[![](https://jitpack.io/v/ibrahimsn98/InputValidator.svg)](https://jitpack.io/#ibrahimsn98/InputValidator)

## Setup
```gradle
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
dependencies {
      implementation 'com.github.ibrahimsn98:InputValidator:1.1'
}
```

## Usage
```kotlin
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText = findViewById<EditText>(R.id.editText)
        val button = findViewById<Button>(R.id.button)

        val password = "123456"
        val text = "lorem ipsum dolor"

        val validator = InputValidator(arrayOf(
            Validator(0, password).notEmpty(),
            Validator(1, text).notEmpty().min(3).max(50).startsWith("l").endsWith("r"),
            Validator(2, editText).notEmpty().email()
        ))
        
        val results = validator.validate()

        //OR

        validator.setOnValidationListener(object: ValidationListener {
            override fun onValidated(validationResult: InputValidator.ValidationResult) {
                if (!validationResult.isValid)
                    for (key in validationResult.errors.keys)
                        Log.d("MainActivity", "[$key]: ${validationResult.errors[key]}")
            }
        })

        button.setOnClickListener { validator.validate() }
    }
}
```
Additionally, you can check is field's values are the same.

```kotlin
 val validator = InputValidator(arrayOf(
            Validator(0, password).notEmpty(),
            Validator(1, confirmPassword).notEmpty(),
            Validator(2, email).notEmpty().email()
        )).completes(0, 1)
```
## Validation Functions
* notEmpty()
* email()
* numeric()
* phoneNumber()
* min(int)
* max(int)
* startsWith(String)
* endsWith(String)
* regex(String)




License
--------

    MIT License

	Copyright (c) 2018 ibrahim süren

	Permission is hereby granted, free of charge, to any person obtaining a copy
	of this software and associated documentation files (the "Software"), to deal
	in the Software without restriction, including without limitation the rights
	to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
	copies of the Software, and to permit persons to whom the Software is
	furnished to do so, subject to the following conditions:

	The above copyright notice and this permission notice shall be included in all
	copies or substantial portions of the Software.

	THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
	IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
	FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
	AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
	LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
	OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
	SOFTWARE.
	© 2018 Git


> Follow me on Twitter [@ibrahimsn98](https://twitter.com/ibrahimsn98)