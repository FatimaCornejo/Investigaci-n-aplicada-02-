package com.example.sqliteapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sqliteapp.databinding.ItemCancionesBinding

data class Cancion(
    val titulo: String,
    val genero: String,
    val duracion: String,
    val album: String,
    val artista: String
)

class CancionAdapter(private val lista: List<Cancion>) : RecyclerView.Adapter<CancionAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemCancionesBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCancionesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = lista.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cancion = lista[position]
        holder.binding.txtTitulo.text = cancion.titulo
        holder.binding.txtGenero.text = "Género: ${cancion.genero}"
        holder.binding.txtDuracion.text = "Duración: ${cancion.duracion}"
        holder.binding.txtAlbum.text = "Álbum: ${cancion.album}"
        holder.binding.txtArtista.text = "Artista: ${cancion.artista}"
    }
}