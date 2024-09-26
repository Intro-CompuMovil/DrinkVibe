package com.example.proyecto

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LogIn : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        val emailVer: EditText = findViewById(R.id.editTextEmail)
        val passwordVer: EditText = findViewById(R.id.editTextPassword)
        val boton=findViewById<Button>(R.id.button3)


        boton.setOnClickListener{

            val email=emailVer.text.toString()
            val password=passwordVer.text.toString()

            if(email.isEmpty()){
                Toast.makeText(this, "Por favor ingrese un email.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(password.isEmpty()){
                Toast.makeText(this, "Por favor ingrese su contraseña.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val intent= Intent(this, discotecasUbi::class.java)
            startActivity(intent)
        }


    }
}