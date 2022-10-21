package com.example.blocnotas.database

import android.content.ClipData
import androidx.room.*

@Dao
interface NoteDao {

    @Query("SELECT * FROM notes ORDER BY note_title ASC")
    fun getAll(): List<Notes>
}