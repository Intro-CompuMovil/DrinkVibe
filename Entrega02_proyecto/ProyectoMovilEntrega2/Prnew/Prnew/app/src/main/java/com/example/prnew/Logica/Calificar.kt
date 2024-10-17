package com.example.prnew.Logica

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.prnew.R
import org.json.JSONObject
import java.io.ByteArrayOutputStream

class Calificar : AppCompatActivity() {

    private lateinit var cameraLauncher: ActivityResultLauncher<Intent>
    private lateinit var galleryLauncher: ActivityResultLauncher<Intent>
    private var photoBitmap: Bitmap? = null // Guardar la foto tomada o subida

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calificar)

        val nombre = intent.getStringExtra("nombre")
        val direccion = intent.getStringExtra("direccion")
        val calificacion = intent.getDoubleExtra("calificacion", 0.0)
        val comentarios = intent.getStringArrayListExtra("comentarios")
        val fotoUrl = intent.getStringExtra("foto")  // Recibimos la URL de la foto

        findViewById<TextView>(R.id.textView10).text = nombre

        val botonTomarFoto = findViewById<Button>(R.id.button2)
        val botonSubirFoto = findViewById<Button>(R.id.button4)
        val imagen = findViewById<ImageView>(R.id.imageView2)
        val calificarButton = findViewById<Button>(R.id.button5)
        val estrellasRatingBar = findViewById<RatingBar>(R.id.ratingBar) // Un RatingBar para seleccionar las estrellas
        val comentarioEditText = findViewById<EditText>(R.id.comentario)

        // Configurar el lanzador para tomar la foto
        cameraLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                photoBitmap = result.data?.extras?.get("data") as Bitmap
                imagen.setImageBitmap(photoBitmap)
            }
        }

        // Configurar el lanzador para seleccionar una imagen desde la galería
        galleryLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK && result.data != null) {
                val imageUri = result.data?.data
                imagen.setImageURI(imageUri)
                // Convertir URI en Bitmap para subirla en el JSON
                imageUri?.let {
                    photoBitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, it)
                }
            }
        }

        // Configurar el botón para tomar la foto
        botonTomarFoto.setOnClickListener {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), CAMERA_PERMISSION_CODE)
            } else {
                openCamera()
            }
        }

        // Configurar el botón para subir una foto
        botonSubirFoto.setOnClickListener {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), STORAGE_PERMISSION_CODE)
            } else {
                openGallery()
            }
        }

        calificarButton.setOnClickListener {
            val estrellas = estrellasRatingBar.rating
            val comentario = comentarioEditText.text.toString()

            // Verificar si se ha tomado o subido una foto
            if (photoBitmap == null) {
                Toast.makeText(this, "Por favor, sube o toma una foto", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Convertir Bitmap a base64 (para simular subir la imagen en JSON)
            val imagenBase64 = bitmapToBase64(photoBitmap!!)

            // Crear el objeto JSON con la calificación
            val jsonObject = JSONObject().apply {
                put("estrellas", estrellas)
                put("comentario", comentario)
                put("imagen", imagenBase64)
            }

            // Simular la "subida" del archivo JSON (en este caso lo imprimimos en logs)
            subirJSON(jsonObject.toString())

            finish()
        }
    }

    // Convertir el Bitmap en una cadena base64
    private fun bitmapToBase64(bitmap: Bitmap): String {
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
        val byteArray = byteArrayOutputStream.toByteArray()
        return Base64.encodeToString(byteArray, Base64.DEFAULT)
    }

    // Método que simula la subida del archivo JSON
    private fun subirJSON(jsonData: String) {
        // Aquí puedes realizar la lógica para enviar el archivo JSON a un servidor
        Log.d("Calificar", "JSON enviado: $jsonData")
        Toast.makeText(this, "Calificación subida correctamente", Toast.LENGTH_SHORT).show()
    }

    // Abrir la cámara para tomar una foto
    private fun openCamera() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraLauncher.launch(cameraIntent)
    }

    // Abrir la galería para seleccionar una imagen
    private fun openGallery() {
        val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        galleryLauncher.launch(galleryIntent)
    }

    companion object {
        private const val CAMERA_PERMISSION_CODE = 100
        private const val STORAGE_PERMISSION_CODE = 101
    }
}