package com.example.pokeprueba.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.example.pokeprueba.R
import com.example.pokeprueba.pokemon.PokemonListActivity
import com.example.pokeprueba.utils.NavigationUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val signup = findViewById<Button>(R.id.sign_up)
        val usernameText = findViewById<EditText>(R.id.username_sign_up)
        val passwordText = findViewById<EditText>(R.id.password_sign_up)
        val signInLink = findViewById<TextView>(R.id.sign_in_link)

        // Lógica para el registro
        signup.setOnClickListener {
            val email = usernameText.text.toString()
            val password = passwordText.text.toString()

            GlobalScope.launch(Dispatchers.Main) {
                val success = AuthController.registerUser(this@SignUpActivity, email, password)
                if (success) {
                    NavigationUtils.redirectToDestination(this@SignUpActivity, PokemonListActivity::class.java)
                } else {
                    Toast.makeText(this@SignUpActivity, "Error al registrarse", Toast.LENGTH_SHORT).show()
                }
            }
        }

        // Redirigir a la vista de inicio de sesión
        signInLink.setOnClickListener {
            NavigationUtils.redirectToDestination(this@SignUpActivity, LoginActivity::class.java)
        }

        // Habilita el botón de registrarse si los campos username y password no están vacíos
        usernameText.addTextChangedListener {
            signup.isEnabled = it?.isNotBlank() == true && passwordText.text.isNotBlank()
        }

        passwordText.addTextChangedListener {
            signup.isEnabled = usernameText.text.isNotBlank() && it?.isNotBlank() == true
        }
    }
}