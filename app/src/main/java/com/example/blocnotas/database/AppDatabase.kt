package com.example.blocnotas.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Notes::class), version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun noteDao(): NoteDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) { //Usamos el operador Elvis para mostrar la instancia existente de ka base de datos si existe o crear la base de datos por primera vez
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "app_database")
                    .createFromAsset("database/bus_schedule.db") //llamamos a createFromAsset para cargar los datos existentes ya que los datos están prepropagados
                    .build()
                INSTANCE = instance

                instance
            }
        }
    }
}