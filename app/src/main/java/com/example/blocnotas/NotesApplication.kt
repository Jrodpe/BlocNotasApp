package com.example.blocnotas

import android.app.Application
import com.example.blocnotas.database.AppDatabase

class NotesApplication: Application() {

    val database: AppDatabase by lazy { AppDatabase.getDatabase(this) }
}