package com.example.proyecto

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class PermisoCamara : AppCompatActivity() {
    private lateinit var imageView: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.permiso_camara)

        imageView = findViewById(R.id.imageView11)
        // Verifica si el permiso ya está concedido
        if (ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            // El permiso ya fue concedido, puedes acceder a la cámara
            openCamera()
        } else {
            // El permiso no ha sido concedido, solicitarlo
            requestCameraPermission()
        }
    }

    // Función para solicitar el permiso de la cámara
    private fun requestCameraPermission() {
        // Verificar si es necesario mostrar una explicación del por qué necesitamos el permiso
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                android.Manifest.permission.CAMERA
            )
        ) {
            // Mostrar una explicación al usuario
            Toast.makeText(this, "Se necesita el permiso para acceder a la cámara", Toast.LENGTH_LONG).show()
        }

        // Solicitar el permiso
        ActivityCompat.requestPermissions(
            this,
            arrayOf(android.Manifest.permission.CAMERA),
            CAMERA_PERMISSION_CODE
        )
    }

    // Manejar la respuesta del usuario al aceptar o denegar el permiso
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            CAMERA_PERMISSION_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permiso concedido, puedes acceder a la cámara
                    openCamera()                } else {
                    // Permiso denegado
                    Toast.makeText(this, "Permiso denegado", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    // Función para abrir la aplicación de cámara
    private fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        // Verificar si hay una app de cámara disponible
        if (intent.resolveActivity(packageManager) != null) {
            startActivityForResult(intent, CAMERA_REQUEST_CODE)
        } else {
            Toast.makeText(this, "No hay aplicación de cámara disponible", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        private const val CAMERA_PERMISSION_CODE = 100
        private const val CAMERA_REQUEST_CODE = 101
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK) {
            // Obtener el Bitmap de la imagen capturada
            val imageBitmap = data?.extras?.get("data") as Bitmap
            // Mostrar la imagen en el ImageView
            imageView.setImageBitmap(imageBitmap)
        }
    }
}