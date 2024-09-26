package com.example.proyecto

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.proyecto.Datos.Datos


class discotecasUbi : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_discotecas_ubi)

        val checkBox1=findViewById<CheckBox>(R.id.checkBox) as CheckBox
        val checkBox2=findViewById<CheckBox>(R.id.checkBox2) as CheckBox
        val checkBox3=findViewById<CheckBox>(R.id.checkBox3) as CheckBox
        val checkBox4=findViewById<CheckBox>(R.id.checkBox4) as CheckBox
        val boton=findViewById<Button>(R.id.button2) as Button
        val permisos = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION)
        val mapa:WebView = findViewById(R.id.imageView3)

        mapa.settings.javaScriptEnabled = true

        mapa.loadUrl("https://www.google.com/maps/place/Pontificia+Universidad+Javeriana/@4.6285035,-74.0642783,17z/data=!4m6!3m5!1s0x8e3f99a8193f1c9f:0xf93431640f134c07!8m2!3d4.6284875!4d-74.0646645!16zL20vMDdteTBi?entry=ttu&g_ep=EgoyMDI0MDkyMy4wIKXMDSoASAFQAw%3D%3D")

        mapa.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                if (url != null) {
                    view?.loadUrl(url)
                }
                return true
            }
        }

        boton.setOnClickListener{
            if(checkBox1.isChecked == true){
                val intent= Intent(this, discoteca::class.java)
                startActivity((intent))
            }else if(checkBox2.isChecked == true){
                val intent= Intent(this, discoteca::class.java)
                startActivity((intent))
            }else if(checkBox3.isChecked == true){
                val intent= Intent(this, discoteca::class.java)
                startActivity((intent))
            }else if(checkBox4.isChecked == true){
                val intent= Intent(this, discoteca::class.java)
                startActivity((intent))
            }
        }

        pedirPermiso(this, permisos,"Se necesitan estos permisos para todas las funcionalidades de la app",
            Datos.MY_PERMISSION_ACCESS_FINE_LOCATION)


    }

    fun pedirPermiso(context: Activity, permisos: Array<String>, justificacion: String, idCode: Int) {

        if(ContextCompat.checkSelfPermission(context,permisos[0]) != PackageManager.PERMISSION_GRANTED){
            if (ActivityCompat.shouldShowRequestPermissionRationale(context, permisos[0])) {
                Toast.makeText(this, "se necesita permiso para esta funcion", Toast.LENGTH_SHORT).show()
                ActivityCompat.requestPermissions(context, arrayOf(permisos[0]), idCode)
            }

            ActivityCompat.requestPermissions(context, permisos, idCode)
        }
        else{

        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            Datos.MY_PERMISSION_ACCESS_FINE_LOCATION -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    Toast.makeText(this, "!Gracias", Toast.LENGTH_SHORT).show()

                } else {
                    Toast.makeText(this, "Funcionalidades Limitadas", Toast.LENGTH_SHORT).show()
                }
                return
            }
            else -> {

            }
        }
    }


}