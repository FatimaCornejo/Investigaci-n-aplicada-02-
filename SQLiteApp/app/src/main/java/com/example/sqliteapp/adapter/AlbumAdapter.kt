package com.example.sqliteapp.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sqliteapp.databinding.ItemAlbumBinding

data class Album(val titulo: String, val anio: Int, val artista: String)

class AlbumAdapter(private val lista: List<Album>) : RecyclerView.Adapter<AlbumAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemAlbumBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemAlbumBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = lista.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val album = lista[position]
        holder.binding.txtTitulo.text = album.titulo
        holder.binding.txtAnio.text = "Año: ${album.anio}"
        holder.binding.txtArtista.text = "Artista: ${album.artista}"
    }
}