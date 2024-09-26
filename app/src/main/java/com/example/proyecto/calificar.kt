package com.example.proyecto

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class calificar : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calificar)

        val boton=findViewById<Button>(R.id.button5)
        val foto=findViewById<Button>(R.id.button4)

        boton.setOnClickListener{
            Toast.makeText(this, "Calificación Registrada", Toast.LENGTH_SHORT).show()
        }
        foto.setOnClickListener{
            val intent=Intent(this, PermisoCamara::class.java)
            startActivity(intent)
        }
    }
}