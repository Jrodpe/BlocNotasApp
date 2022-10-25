package com.example.blocnotas.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface NoteDao {
    @Query("SELECT * FROM Notes ORDER BY note_title ASC")
    fun getAll(): List<Notes>

    @Insert (onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(notes: Notes)

    /*@Update
    suspend fun update(notes: Notes)

    @Delete
    suspend fun delete(notes: Notes)

     */
}