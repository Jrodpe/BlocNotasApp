package com.example.blocnotas.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import com.example.blocnotas.R
import com.example.blocnotas.database.NoteDao
import com.example.blocnotas.database.Notes
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch


class NotesViewModel(private val noteDao: NoteDao) : ViewModel() {

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

    fun getNote(noteId: Int, noteTitle: String, noteText: String): Notes {
        return Notes(
            id = noteId,
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

    fun editNote(noteId: Int, noteTitle: String, noteText: String){
        val note = getNote(noteId, noteTitle, noteText)
        updateNote(note)
    }

    fun updateNote(notes: Notes){
        viewModelScope.launch{
            noteDao.update(notes)
        }
    }

    /**
     * Launching a new coroutine to delete an item in a non-blocking way
     */
    fun deleteNote(notes: Notes) {
        viewModelScope.launch {
            noteDao.delete(notes)
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