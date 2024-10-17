package com.example.prnew.Logica

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.prnew.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStreamReader

class DiscotecasUbiActivity : AppCompatActivity() {

    private lateinit var discotecaAdapter: DiscotecaAdapter
    private lateinit var discotecas: List<Discoteca>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_discotecas_ubi)

        val botonPerfil = findViewById<ImageButton>(R.id.imageButton3)

        try {
            // Leer el archivo JSON
            val inputStream = assets.open("discotecas.json")
            val reader = InputStreamReader(inputStream)
            val discotecasType = object : TypeToken<List<Discoteca>>() {}.type
            discotecas = Gson().fromJson(reader, discotecasType)
            reader.close()

            // Configurar el RecyclerView
            val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
            recyclerView.layoutManager = LinearLayoutManager(this)
            discotecaAdapter = DiscotecaAdapter(discotecas) { discoteca ->
                val intent = Intent(this, DiscotecaDetailActivity::class.java).apply {
                    putExtra("nombre", discoteca.nombre)
                    putExtra("direccion", discoteca.direccion)
                    putExtra("calificacion", discoteca.calificacion)
                    putStringArrayListExtra("comentarios", ArrayList(discoteca.comentarios))
                    putExtra("foto", discoteca.foto)  // Enviar la URL de la foto
                }
                startActivity(intent)
            }
            recyclerView.adapter = discotecaAdapter

        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(this, "Error al cargar las discotecas", Toast.LENGTH_SHORT).show()
            finish()
        }

        // Configurar la barra de búsqueda
        val searchView = findViewById<SearchView>(R.id.search_view)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val filteredList = discotecas.filter {
                    it.nombre.contains(newText ?: "", ignoreCase = true)
                }
                discotecaAdapter.updateList(filteredList)
                return true
            }
        })

        botonPerfil.setOnClickListener{
            val intent = Intent(this, Perfil::class.java)
            startActivity(intent)
        }
    }
}
