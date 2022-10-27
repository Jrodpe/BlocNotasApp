package com.example.blocnotas.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Query("SELECT * FROM Notes ORDER BY note_title")
    fun getAll(): Flow<List<Notes>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(notes: Notes)

    @Delete
    suspend fun delete(notes: Notes)

    @Update
    suspend fun update(notes: Notes)

}