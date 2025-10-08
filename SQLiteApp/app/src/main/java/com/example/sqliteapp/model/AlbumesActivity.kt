package com.example.sqliteapp

import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sqliteapp.adapter.Album
import com.example.sqliteapp.adapter.AlbumAdapter
import com.example.sqliteapp.databinding.ActivityAlbumesBinding
import com.example.sqliteapp.db.HelperDB

class AlbumesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAlbumesBinding
    private lateinit var db: SQLiteDatabase
    private val listaAlbumes = mutableListOf<Album>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlbumesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = HelperDB(this).readableDatabase

        cargarFiltroArtistas()
        cargarAlbumes()

        binding.txtBuscar.setOnEditorActionListener { _, _, _ ->
            cargarAlbumes()
            true
        }

        val listener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                cargarAlbumes()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // No hacer nada
            }
        }

        binding.spnArtista.onItemSelectedListener = listener
    }

    private fun cargarFiltroArtistas() {
        val artistas = listOf("Todos", "Coldplay", "Shakira")
        binding.spnArtista.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, artistas)
    }

    private fun cargarAlbumes() {
        listaAlbumes.clear()

        val titulo = binding.txtBuscar.text.toString().trim()
        val artista = binding.spnArtista.selectedItem?.toString()?.trim() ?: "Todos"

        val query = """
        SELECT a.titulo, a.anio, ar.nombre AS artista
        FROM album a
        JOIN artista ar ON a.idartista = ar.idartista
        WHERE a.titulo LIKE ? AND
              (? = 'Todos' OR ar.nombre = ?)
    """

        val cursor = db.rawQuery(query, arrayOf("%$titulo%", artista, artista))

        while (cursor.moveToNext()) {
            val album = Album(
                titulo = cursor.getString(cursor.getColumnIndexOrThrow("titulo")),
                anio = cursor.getInt(cursor.getColumnIndexOrThrow("anio")),
                artista = cursor.getString(cursor.getColumnIndexOrThrow("artista"))
            )
            listaAlbumes.add(album)
        }
        cursor.close()

        binding.rvAlbumes.layoutManager = LinearLayoutManager(this)
        binding.rvAlbumes.adapter = AlbumAdapter(listaAlbumes)
    }
}