package com.example.pokeprueba.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.pokeprueba.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.example.pokeprueba.pokemon.PokemonListActivity
import com.example.pokeprueba.utils.NavigationUtils

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Comprobar si existe una sesión
        val currentUser = AuthController.getCurrentUser()
        if (currentUser != null) {
            val intent = Intent(this, PokemonListActivity::class.java)
            startActivity(intent)
            finish()
        }

        val login = findViewById<Button>(R.id.login)
        val usernameText = findViewById<EditText>(R.id.username)
        val passwordText = findViewById<EditText>(R.id.password)
        val signUpLink = findViewById<TextView>(R.id.sign_up_link)

        // Lógica para el inicio de sesión
        login.setOnClickListener {
            val email = usernameText.text.toString()
            val password = passwordText.text.toString()

            GlobalScope.launch(Dispatchers.Main) {
                val success = AuthController.loginUser(this@LoginActivity, email, password)
                if (success) {
                    NavigationUtils.redirectToDestination(this@LoginActivity, PokemonListActivity::class.java)
                } else {
                    Toast.makeText(this@LoginActivity, "Error al iniciar sesión", Toast.LENGTH_SHORT).show()
                }
            }
        }

        // Redirigir a la vista de registro
        signUpLink.setOnClickListener {
            NavigationUtils.redirectToDestination(this@LoginActivity, SignUpActivity::class.java)
        }

        // Habilita el botón de inicio de sesión si los campos username y password no están vacíos
        usernameText.addTextChangedListener {
            login.isEnabled = it?.isNotBlank() == true && passwordText.text.isNotBlank()
        }

        passwordText.addTextChangedListener {
            login.isEnabled = usernameText.text.isNotBlank() && it?.isNotBlank() == true
        }
    }
}
