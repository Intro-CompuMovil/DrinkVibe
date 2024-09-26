package com.example.proyecto

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class discoteca : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_discoteca)

        val boton1=findViewById<Button>(R.id.menu)
        val boton2=findViewById<Button>(R.id.reserva)
        val boton3=findViewById<Button>(R.id.calificar)
        val boton4=findViewById<Button>(R.id.guia)
        val mapa2:WebView = findViewById(R.id.imageView5)

        mapa2.settings.javaScriptEnabled = true
        mapa2.loadUrl("https://www.google.com/maps/dir/Pontificia+Universidad+Javeriana,+Carrera+7,+Bogot%C3%A1/Monarca+Disco+Club,+Kr+13+%2382-52,+Bogot%C3%A1/@4.6488398,-74.0666321,14z/data=!4m14!4m13!1m5!1m1!1s0x8e3f99a8193f1c9f:0xf93431640f134c07!2m2!1d-74.0646645!2d4.6284875!1m5!1m1!1s0x8e3f9bb7517b6ded:0x1e746be0c7eabb76!2m2!1d-74.0544886!2d4.6672587!3e0?entry=ttu&g_ep=EgoyMDI0MDkyMy4wIKXMDSoASAFQAw%3D%3D")
        mapa2.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                if (url != null) {
                    view?.loadUrl(url)
                }
                return true
            }
        }

        boton1.setOnClickListener{
            val intent= Intent(this, Menu::class.java)
            startActivity(intent)
        }

        boton2.setOnClickListener{
            val intent= Intent(this, reserva::class.java)
            startActivity(intent)
        }

        boton3.setOnClickListener{
            val intent= Intent(this, calificar::class.java)
            startActivity(intent)
        }

        boton4.setOnClickListener{
            val intent2=Intent(this, mapa::class.java)
            startActivity(intent2)
        }
    }
}