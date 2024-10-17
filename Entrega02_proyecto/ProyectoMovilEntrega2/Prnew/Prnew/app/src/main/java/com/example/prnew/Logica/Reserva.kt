package com.example.prnew.Logica

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.prnew.R

class Reserva : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reserva)

        val reservarButton = findViewById<Button>(R.id.button8)

        reservarButton.setOnClickListener {
            Toast.makeText(this, "Reservado con exito", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}