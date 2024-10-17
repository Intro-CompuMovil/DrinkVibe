package com.example.prnew.Logica

import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.prnew.R

// Adaptador para el RecyclerView de discotecas
class DiscotecaAdapter(
    private var discotecas: List<Discoteca>,  // Lista de discotecas a mostrar
    private val onItemClick: (Discoteca) -> Unit  // Listener para manejar clics en una discoteca
) : RecyclerView.Adapter<DiscotecaAdapter.DiscotecaViewHolder>() {

    // ViewHolder que representa cada elemento de la lista
    inner class DiscotecaViewHolder(itemView: LinearLayout) : RecyclerView.ViewHolder(itemView) {
        val nombreTextView: TextView = itemView.findViewById(R.id.nombre)

        // Método para vincular los datos de la discoteca con las vistas
        fun bind(discoteca: Discoteca) {
            nombreTextView.text = discoteca.nombre

            itemView.setOnClickListener {
                onItemClick(discoteca)
            }
        }
    }

    // Crear cada elemento sin necesidad de un archivo XML separado
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiscotecaViewHolder {
        // Definimos el layout del item programáticamente
        val itemLayout = LinearLayout(parent.context).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            orientation = LinearLayout.VERTICAL
            setPadding(8, 8, 8, 8)  // Espaciado interno

            // Agregamos el TextView programáticamente
            val nombreTextView = TextView(context).apply {
                id = R.id.nombre  // Asegúrate de que este ID esté definido en tu proyecto
                textSize = 18f
                setTextColor(context.getColor(android.R.color.black))
                setTypeface(typeface, android.graphics.Typeface.BOLD)
            }

            addView(nombreTextView)  // Añadimos el TextView al LinearLayout
        }

        return DiscotecaViewHolder(itemLayout)
    }

    // Vincular cada elemento de la lista con los datos de la discoteca correspondiente
    override fun onBindViewHolder(holder: DiscotecaViewHolder, position: Int) {
        holder.bind(discotecas[position])
    }

    // Indica cuántos elementos tiene la lista
    override fun getItemCount(): Int = discotecas.size

    // Método para actualizar la lista de discotecas cuando se realiza una búsqueda
    fun updateList(newList: List<Discoteca>) {
        discotecas = newList
        notifyDataSetChanged()  // Notificar al adaptador que los datos han cambiado
    }
}
