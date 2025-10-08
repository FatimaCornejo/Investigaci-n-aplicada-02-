package com.example.sqliteapp


import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sqliteapp.adapter.Artista
import com.example.sqliteapp.adapter.ArtistaAdapter
import com.example.sqliteapp.databinding.ActivityArtistasBinding
import com.example.sqliteapp.db.HelperDB

class ArtistasActivity : AppCompatActivity() {

    private lateinit var binding: ActivityArtistasBinding
    private lateinit var db: SQLiteDatabase
    private val listaArtistas = mutableListOf<Artista>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArtistasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = HelperDB(this).readableDatabase

        cargarArtistas()

        binding.txtBuscar.setOnEditorActionListener { _, _, _ ->
            cargarArtistas()
            true
        }
    }

    private fun cargarArtistas() {
        listaArtistas.clear()
        val nombre = binding.txtBuscar.text.toString()

        val cursor = db.rawQuery(
            "SELECT nombre, pais FROM artista WHERE nombre LIKE ?",
            arrayOf("%$nombre%")
        )

        while (cursor.moveToNext()) {
            val artista = Artista(
                nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre")),
                pais = cursor.getString(cursor.getColumnIndexOrThrow("pais"))
            )
            listaArtistas.add(artista)
        }
        cursor.close()

        binding.rvArtistas.layoutManager = LinearLayoutManager(this)
        binding.rvArtistas.adapter = ArtistaAdapter(listaArtistas)
    }
}