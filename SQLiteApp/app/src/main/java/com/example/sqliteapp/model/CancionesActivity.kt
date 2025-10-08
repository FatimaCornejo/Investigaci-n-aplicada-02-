package com.example.sqliteapp

import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sqliteapp.adapter.Cancion
import com.example.sqliteapp.adapter.CancionAdapter
import com.example.sqliteapp.databinding.ActivityCancionesBinding
import com.example.sqliteapp.db.HelperDB

class CancionesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCancionesBinding
    private lateinit var db: SQLiteDatabase
    private val listaCanciones = mutableListOf<Cancion>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCancionesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = HelperDB(this).readableDatabase

        cargarFiltros()
        cargarCanciones()

        binding.txtBuscar.setOnEditorActionListener { _, _, _ ->
            cargarCanciones()
            true
        }

        val listener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                cargarCanciones()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // No hacer nada
            }
        }

        binding.spnGenero.onItemSelectedListener = listener
        binding.spnArtista.onItemSelectedListener = listener
        binding.spnAlbum.onItemSelectedListener = listener
    }

    private fun cargarFiltros() {
        val generos = listOf("Todos", "Rock", "Pop")
        val artistas = listOf("Todos", "Coldplay", "Shakira")
        val albumes = listOf("Todos", "Parachutes", "Laundry Service")

        binding.spnGenero.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, generos)
        binding.spnArtista.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, artistas)
        binding.spnAlbum.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, albumes)
    }

    private fun cargarCanciones() {
        listaCanciones.clear()

        val titulo = binding.txtBuscar.text.toString()
        val genero = binding.spnGenero.selectedItem.toString()
        val artista = binding.spnArtista.selectedItem.toString()
        val album = binding.spnAlbum.selectedItem.toString()

        val query = """
            SELECT c.titulo, c.genero, c.duracion, a.titulo AS album, ar.nombre AS artista
            FROM cancion c
            JOIN album a ON c.idalbum = a.idalbum
            JOIN artista ar ON a.idartista = ar.idartista
            WHERE c.titulo LIKE ? AND
                  (? = 'Todos' OR c.genero = ?) AND
                  (? = 'Todos' OR ar.nombre = ?) AND
                  (? = 'Todos' OR a.titulo = ?)
        """

        val cursor = db.rawQuery(query, arrayOf("%$titulo%", genero, genero, artista, artista, album, album))

        while (cursor.moveToNext()) {
            val cancion = Cancion(
                titulo = cursor.getString(cursor.getColumnIndexOrThrow("titulo")),
                genero = cursor.getString(cursor.getColumnIndexOrThrow("genero")),
                duracion = cursor.getString(cursor.getColumnIndexOrThrow("duracion")),
                album = cursor.getString(cursor.getColumnIndexOrThrow("album")),
                artista = cursor.getString(cursor.getColumnIndexOrThrow("artista"))
            )
            listaCanciones.add(cancion)
        }
        cursor.close()

        binding.rvCanciones.layoutManager = LinearLayoutManager(this)
        binding.rvCanciones.adapter = CancionAdapter(listaCanciones)
    }
}