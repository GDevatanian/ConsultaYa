package com.ar.consultaya

import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.MotionEvent
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val etUser = findViewById<EditText>(R.id.etUser)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val btnRegister = findViewById<Button>(R.id.btnRegister)
        val tvForgotPassword = findViewById<android.widget.TextView>(R.id.tvForgotPassword)

        // Lógica del ojito de contraseña
        etPassword.setOnTouchListener { v, event ->
            val DRAWABLE_RIGHT = 2
            if (event.action == MotionEvent.ACTION_UP) {
                if (event.rawX >= (etPassword.right - etPassword.compoundDrawables[DRAWABLE_RIGHT].bounds.width())) {
                    if (etPassword.transformationMethod is PasswordTransformationMethod) {
                        // Mostrar contraseña
                        etPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
                        etPassword.setCompoundDrawablesWithIntrinsicBounds(
                            R.drawable.rounded_field, // Hack: necesitamos preservar los otros drawables, esto está mal.
                            0,
                            R.drawable.ic_eye_visible,
                            0
                        )
                        // Forma correcta: obtener los drawables actuales y solo cambiar el derecho
                        val drawables = etPassword.compoundDrawables
                        etPassword.setCompoundDrawablesWithIntrinsicBounds(drawables[0], drawables[1], getDrawable(R.drawable.ic_eye_visible), drawables[3])

                    } else {
                        // Ocultar contraseña
                        etPassword.transformationMethod = PasswordTransformationMethod.getInstance()
                        val drawables = etPassword.compoundDrawables
                        etPassword.setCompoundDrawablesWithIntrinsicBounds(drawables[0], drawables[1], getDrawable(R.drawable.ic_eye_hidden), drawables[3])
                    }
                    // Mantener el tinte del icono
                    etPassword.compoundDrawables[2]?.setTint(getColor(R.color.teal_icon))
                    
                    return@setOnTouchListener true
                }
            }
            false
        }

        btnLogin.setOnClickListener {
            val user = etUser.text.toString()
            val password = etPassword.text.toString()

            var hasError = false

            if (user == "error") {
                etUser.error = "Usuario inválido"
                hasError = true
            }

            if (password == "error") {
                etPassword.text.clear()
                etPassword.error = "Contraseña incorrecta"
                hasError = true
            }

            if (!hasError) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        btnRegister.setOnClickListener {
            android.widget.Toast.makeText(this, "Funcionalidad de registro próximamente", android.widget.Toast.LENGTH_SHORT).show()
        }

        tvForgotPassword.setOnClickListener {
            android.widget.Toast.makeText(this, "Recuperar contraseña", android.widget.Toast.LENGTH_SHORT).show()
        }
    }
}

