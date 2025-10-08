package com.example.sqliteapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sqliteapp.databinding.ItemArtistaBinding

data class Artista(val nombre: String, val pais: String)

class ArtistaAdapter(private val lista: List<Artista>) : RecyclerView.Adapter<ArtistaAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemArtistaBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemArtistaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = lista.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val artista = lista[position]
        holder.binding.txtNombre.text = artista.nombre
        holder.binding.txtPais.text = "País: ${artista.pais}"
    }
}