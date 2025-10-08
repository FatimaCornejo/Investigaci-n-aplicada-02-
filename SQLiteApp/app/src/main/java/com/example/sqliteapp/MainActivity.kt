package com.example.sqliteapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sqliteapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCanciones.setOnClickListener {
            startActivity(Intent(this, CancionesActivity::class.java))
        }

        binding.btnAlbumes.setOnClickListener {
            startActivity(Intent(this, AlbumesActivity::class.java))
        }

        binding.btnArtistas.setOnClickListener {
            startActivity(Intent(this, ArtistasActivity::class.java))
        }
    }
}