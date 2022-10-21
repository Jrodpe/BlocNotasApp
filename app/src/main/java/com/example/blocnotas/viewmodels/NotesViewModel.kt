package com.example.blocnotas.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.blocnotas.database.NoteDao
import com.example.blocnotas.database.Notes


class NotesViewModel(private val noteDao: NoteDao): ViewModel() {

    fun createNote (noteTitle: String, noteText: String){
        Log.d("newNote", "noteTitle= $noteTitle noteText= $noteText")
    }

    fun allSchedule(): List<Notes> = noteDao.getAll()
}

class NotesViewModelFactory(
    private val noteDao: NoteDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NotesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NotesViewModel(noteDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}