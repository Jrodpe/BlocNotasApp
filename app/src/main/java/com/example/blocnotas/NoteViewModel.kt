package com.example.blocnotas

import android.content.ClipData
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.blocnotas.database.NoteDao
import com.example.blocnotas.database.Notes
import kotlinx.coroutines.launch

class NoteViewModel(private val noteDao: NoteDao): ViewModel() {

    fun createNote (noteTitle: String, noteText: String){
        Log.d("newNote", "noteTitle= $noteTitle noteText= $noteText")
    }

    fun fullNotes(): List<Notes> = noteDao.getAll()
}

class NoteViewModelFactory(private val noteDao: NoteDao): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NoteViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NoteViewModel(noteDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

