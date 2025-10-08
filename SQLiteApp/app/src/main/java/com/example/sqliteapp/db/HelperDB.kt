package com.example.sqliteapp.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class HelperDB(context: Context?) :
    SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    companion object {
        private const val DB_NAME = "tienda.sqlite"
        private const val DB_VERSION = 2
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("""
            CREATE TABLE artista (
                idartista INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre TEXT,
                pais TEXT
            )
        """.trimIndent())

        db.execSQL("""
            CREATE TABLE album (
                idalbum INTEGER PRIMARY KEY AUTOINCREMENT,
                titulo TEXT,
                anio INTEGER,
                idartista INTEGER,
                FOREIGN KEY(idartista) REFERENCES artista(idartista)
            )
        """.trimIndent())

        db.execSQL("""
            CREATE TABLE cancion (
                idcancion INTEGER PRIMARY KEY AUTOINCREMENT,
                titulo TEXT,
                genero TEXT,
                duracion TEXT,
                idalbum INTEGER,
                FOREIGN KEY(idalbum) REFERENCES album(idalbum)
            )
        """.trimIndent())

        // Artistas
        db.execSQL("INSERT INTO artista (nombre, pais) VALUES ('Coldplay', 'UK')")
        db.execSQL("INSERT INTO artista (nombre, pais) VALUES ('Shakira', 'Colombia')")
        db.execSQL("INSERT INTO artista (nombre, pais) VALUES ('Romeo Santos', 'USA')")
        db.execSQL("INSERT INTO artista (nombre, pais) VALUES ('Prince Royce', 'USA')")
        db.execSQL("INSERT INTO artista (nombre, pais) VALUES ('Juan Luis Guerra', 'República Dominicana')")
        db.execSQL("INSERT INTO artista (nombre, pais) VALUES ('Blackpink', 'Corea del Sur')")
        db.execSQL("INSERT INTO artista (nombre, pais) VALUES ('BTS', 'Corea del Sur')")
        db.execSQL("INSERT INTO artista (nombre, pais) VALUES ('TWICE', 'Corea del Sur')")

        // Álbumes
        db.execSQL("INSERT INTO album VALUES (null, 'Parachutes', 2000, 1)")
        db.execSQL("INSERT INTO album VALUES (null, 'Laundry Service', 2001, 2)")
        db.execSQL("INSERT INTO album VALUES (null, 'Formula, Vol. 1', 2011, 3)")
        db.execSQL("INSERT INTO album VALUES (null, 'Prince Royce', 2010, 4)")
        db.execSQL("INSERT INTO album VALUES (null, 'Bachata Rosa', 1990, 5)")
        db.execSQL("INSERT INTO album VALUES (null, 'The Album', 2020, 6)")
        db.execSQL("INSERT INTO album VALUES (null, 'BE', 2020, 7)")
        db.execSQL("INSERT INTO album VALUES (null, 'Formula of Love: O+T=<3', 2021, 8)")

        // Canciones
        db.execSQL("INSERT INTO cancion VALUES (null, 'Yellow', 'Rock', '4:29', 1)")
        db.execSQL("INSERT INTO cancion VALUES (null, 'Whenever, Wherever', 'Pop', '3:16', 2)")

        db.execSQL("INSERT INTO cancion VALUES (null, 'You', 'Bachata', '3:45', 3)")
        db.execSQL("INSERT INTO cancion VALUES (null, 'Promise (feat. Usher)', 'Bachata', '4:10', 3)")
        db.execSQL("INSERT INTO cancion VALUES (null, 'La Diabla', 'Bachata', '3:50', 3)")

        db.execSQL("INSERT INTO cancion VALUES (null, 'Stand by Me', 'Bachata', '3:50', 4)")
        db.execSQL("INSERT INTO cancion VALUES (null, 'Corazón Sin Cara', 'Bachata', '3:45', 4)")
        db.execSQL("INSERT INTO cancion VALUES (null, 'Tu y Yo', 'Bachata', '3:35', 4)")

        db.execSQL("INSERT INTO cancion VALUES (null, 'Bachata Rosa', 'Bachata', '4:00', 5)")
        db.execSQL("INSERT INTO cancion VALUES (null, 'Burbujas de Amor', 'Bachata', '4:10', 5)")
        db.execSQL("INSERT INTO cancion VALUES (null, 'Estrellitas y Duendes', 'Bachata', '4:05', 5)")

        db.execSQL("INSERT INTO cancion VALUES (null, 'How You Like That', 'K-Pop', '3:00', 6)")
        db.execSQL("INSERT INTO cancion VALUES (null, 'Lovesick Girls', 'K-Pop', '3:15', 6)")
        db.execSQL("INSERT INTO cancion VALUES (null, 'Ice Cream (feat. Selena Gomez)', 'K-Pop', '2:55', 6)")

        db.execSQL("INSERT INTO cancion VALUES (null, 'Life Goes On', 'K-Pop', '3:20', 7)")
        db.execSQL("INSERT INTO cancion VALUES (null, 'Dynamite', 'K-Pop', '3:19', 7)")
        db.execSQL("INSERT INTO cancion VALUES (null, 'Telepathy', 'K-Pop', '3:10', 7)")

        db.execSQL("INSERT INTO cancion VALUES (null, 'The Feels', 'K-Pop', '3:30', 8)")
        db.execSQL("INSERT INTO cancion VALUES (null, 'Scientist', 'K-Pop', '3:25', 8)")
        db.execSQL("INSERT INTO cancion VALUES (null, 'Moonlight', 'K-Pop', '3:15', 8)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS cancion")
        db.execSQL("DROP TABLE IF EXISTS album")
        db.execSQL("DROP TABLE IF EXISTS artista")
        onCreate(db)
    }
}