package com.example.prnew.Logica

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.prnew.R
import org.json.JSONObject
import java.io.IOException

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val emailVer: EditText = findViewById(R.id.editTextEmail)
        val passwordVer: EditText = findViewById(R.id.editTextPassword)
        val boton = findViewById<Button>(R.id.button3)

        boton.setOnClickListener {
            val email = emailVer.text.toString()
            val password = passwordVer.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Por favor complete todos los campos.", Toast.LENGTH_SHORT).show()
            } else {
                // Verificar las credenciales con el JSON
                val usuario = verificarCredenciales(email, password)
                if (usuario != null) {
                    // Si las credenciales coinciden, guarda los datos del usuario en el Singleton
                    InformacionUsuario.UserSession.nombreCompleto = usuario.getString("nombreCompleto")
                    InformacionUsuario.UserSession.edad = usuario.getInt("edad")
                    InformacionUsuario.UserSession.email = usuario.getString("email")
                    InformacionUsuario.UserSession.contrasena = usuario.getString("contrasena")

                    // Ahora inicias la actividad que quieras
                    val intent = Intent(this, DiscotecasUbiActivity::class.java) // O a la actividad que necesites
                    startActivity(intent)
                    finish()
                } else {
                    // Si las credenciales no coinciden, mostrar un mensaje de error
                    Toast.makeText(this, "Correo o contraseña incorrectos", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    // Función para verificar las credenciales con el JSON y devolver el usuario si existe
    private fun verificarCredenciales(email: String, password: String): JSONObject? {
        try {
            // Leer el archivo JSON desde la carpeta assets
            val json = cargarJSONDesdeAssets("usuarios.json")
            val jsonObject = JSONObject(json)
            val usuariosArray = jsonObject.getJSONArray("usuarios")

            // Iterar sobre el array de usuarios
            for (i in 0 until usuariosArray.length()) {
                val usuario = usuariosArray.getJSONObject(i)
                val emailUsuario = usuario.getString("email")
                val passwordUsuario = usuario.getString("contrasena")

                // Comprobar si el email y la contraseña coinciden
                if (email == emailUsuario && password == passwordUsuario) {
                    return usuario  // Devolver el objeto usuario
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null  // Credenciales incorrectas
    }

    // Función para cargar el JSON desde la carpeta assets
    private fun cargarJSONDesdeAssets(fileName: String): String? {
        var json: String? = null
        try {
            val inputStream = assets.open(fileName)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, Charsets.UTF_8)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return json
    }
}
