package com.example.blocnotas.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.blocnotas.database.NoteDao
import com.example.blocnotas.database.Notes
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch


class NotesViewModel(private val noteDao: NoteDao) : ViewModel() {

    fun createNote(noteTitle: String, noteText: String) {
        Log.d("newNote", "noteTitle= $noteTitle noteText= $noteText")
    }

    //TODO INSERTAR LA NOTA EN LA BASE DE DATOS
    /**
     * Inserts the new Item into database.
     */
    fun addNewNote(noteTitle: String, noteText: String) {
        Log.d("newNote", "noteTitle= $noteTitle noteText= $noteText")
        val newNote = getNewNoteEntry(noteTitle, noteText)
        insertNote(newNote)
    }

    /**
     * Returns an instance of the [Item] entity class with the item info entered by the user.
     * This will be used to add a new entry to the Inventory database.
     */
    private fun getNewNoteEntry(noteTitle: String, noteText: String): Notes {
        return Notes(
            noteTitle = noteTitle,
            noteText = noteText
        )

    }

    /**
     * Launching a new coroutine to insert an item in a non-blocking way
     */
    private fun insertNote(notes: Notes) {
        viewModelScope.launch {
            noteDao.insert(notes)
        }
    }

    fun allNotes(): Flow<List<Notes>> = noteDao.getAll()

    fun isEntryValid(noteTitle: String, noteText: String): Boolean {
        if (noteTitle.isBlank() || noteText.isBlank()) {
            return false
        }
        return true
    }
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