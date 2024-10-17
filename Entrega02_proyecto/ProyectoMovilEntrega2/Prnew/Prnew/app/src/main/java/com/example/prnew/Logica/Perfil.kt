package com.example.prnew.Logica

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.prnew.R

class Perfil : AppCompatActivity() {
    private lateinit var imageViewPerfil: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)
        val bottonCerrarSesion : Button = findViewById(R.id.bottonCerrarSesion)

        bottonCerrarSesion.setOnClickListener{
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        // Obtener las referencias a los TextViews
        val nombreCompleto = InformacionUsuario.UserSession.nombreCompleto ?: "Nombre no disponible"
        val edad = InformacionUsuario.UserSession.edad
        val email = InformacionUsuario.UserSession.email ?: "Email no disponible"
        val contrasena = InformacionUsuario.UserSession.contrasena ?: "Contraseña no disponible"

        // Cargar la información en los TextViews
        findViewById<TextView>(R.id.textView13).text = nombreCompleto
        findViewById<TextView>(R.id.textView14).text = edad.toString()
        findViewById<TextView>(R.id.textView15).text = email
        findViewById<TextView>(R.id.textView16).text = contrasena


        imageViewPerfil = findViewById(R.id.imageView3)

        // Botón para tomar una foto
        findViewById<Button>(R.id.button6).setOnClickListener {
            tomarFoto()
        }

        // Botón para subir una foto
        findViewById<Button>(R.id.button7).setOnClickListener {
            subirFoto()
        }
    }

    // Método para tomar una foto
    private fun tomarFoto() {
        // Aquí puedes implementar la lógica para abrir la cámara
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)
    }

    // Método para subir una foto
    private fun subirFoto() {
        // Aquí puedes implementar la lógica para abrir el selector de imágenes
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, REQUEST_IMAGE_PICK)
    }

    // Manejar el resultado de la actividad
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            when (requestCode) {
                REQUEST_IMAGE_CAPTURE -> {
                    val imageBitmap = data?.extras?.get("data") as Bitmap
                    imageViewPerfil.setImageBitmap(imageBitmap)
                    imageViewPerfil.visibility = View.VISIBLE
                }
                REQUEST_IMAGE_PICK -> {
                    val selectedImageUri = data?.data
                    imageViewPerfil.setImageURI(selectedImageUri)
                    imageViewPerfil.visibility = View.VISIBLE
                }
            }
        }
    }

    companion object {
        private const val REQUEST_IMAGE_CAPTURE = 1
        private const val REQUEST_IMAGE_PICK = 2
    }
}

