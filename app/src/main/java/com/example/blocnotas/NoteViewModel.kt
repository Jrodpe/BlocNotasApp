package com.example.blocnotas

import android.content.ClipData
import android.util.Log
import androidx.lifecycle.ViewModel


class NoteViewModel(): ViewModel() {

    fun createNote (noteTitle: String, noteText: String){
        Log.d("newNote", "noteTitle= $noteTitle noteText= $noteText")
    }

}



