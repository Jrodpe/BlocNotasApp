package com.example.blocnotas.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Notes")
data class Notes(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "note_title") val noteTitle: String,
    @ColumnInfo(name = "note_text") val noteText: String
)