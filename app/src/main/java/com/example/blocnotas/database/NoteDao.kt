package com.example.blocnotas.database

import androidx.room.Dao
import androidx.room.Query

@Dao
interface NoteDao {

    @Query("SELECT * FROM notes ORDER BY note_title ASC")
    fun getAll(): List<Notes>
}