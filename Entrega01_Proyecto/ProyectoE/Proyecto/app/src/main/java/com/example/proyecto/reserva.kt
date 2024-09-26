package com.example.proyecto

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class reserva : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reserva)

        val correo:EditText= findViewById<EditText>(R.id.email)
        val telefono:EditText=findViewById<EditText>(R.id.phone)
        val nombre:EditText=findViewById<EditText>(R.id.name)
        val personas:EditText=findViewById<EditText>(R.id.number_of_people)

        val boton=findViewById<Button>(R.id.btn_reserve)

        boton.setOnClickListener{
            val email=correo.text.toString()
            val phone=telefono.text.toString()
            val name=nombre.text.toString()
            val people=personas.text.toString()

            if(email.isEmpty()||phone.isEmpty()||name.isEmpty()||people.isEmpty()){
                Toast.makeText(this, "Por favor llene todos los campos.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }else{
                Toast.makeText(this,"Se registró la reserva con exito.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}