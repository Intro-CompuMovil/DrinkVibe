package com.example.prnew.Logica

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide  // Usando Glide para cargar imágenes desde URLs
import com.example.prnew.R

class DiscotecaDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_discoteca_detail)

        // Obtener los datos del Intent
        val nombre = intent.getStringExtra("nombre")
        val direccion = intent.getStringExtra("direccion")
        val calificacion = intent.getDoubleExtra("calificacion", 0.0)
        val comentarios = intent.getStringArrayListExtra("comentarios")
        val fotoUrl = intent.getStringExtra("foto")  // Recibimos la URL de la foto

        // Asignar los datos a las vistas
        findViewById<TextView>(R.id.nombreTextView).text = nombre
        findViewById<TextView>(R.id.direccionTextView).text = direccion
        findViewById<TextView>(R.id.calificacionTextView).text = "Calificación: $calificacion"

        val botonMenu = findViewById<Button>(R.id.menuButton)
        val botonLlegar = findViewById<Button>(R.id.llegarButton)
        val botonReserva = findViewById<Button>(R.id.reservarButton)
        val botonCalificar = findViewById<Button>(R.id.calificarButton)

        val comentariosTextView = findViewById<TextView>(R.id.comentariosTextView)
        comentariosTextView.text = comentarios?.joinToString(separator = "\n") ?: "Sin comentarios"

        // Cargar la imagen desde la URL usando Glide
        val imageView = findViewById<ImageView>(R.id.fotoImageView)
        Glide.with(this)
            .load(fotoUrl)  // Cargamos la imagen desde la URL recibida
            .into(imageView)  // Asignamos la imagen al ImageView

        // Configurar los botones
        botonMenu.setOnClickListener {
            val intent = Intent(this, Menu::class.java)
            startActivity(intent)
        }

        botonLlegar.setOnClickListener {
            val intent = Intent(this, MapaConGoogle::class.java)
            startActivity(intent)
        }

        botonCalificar.setOnClickListener {
            val intent = Intent(this, Calificar::class.java)
            startActivity(intent)
        }

        botonReserva.setOnClickListener {
            val intent = Intent(this, Reserva::class.java)
            startActivity(intent)
        }
    }
}
