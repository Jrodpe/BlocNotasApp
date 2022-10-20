package com.example.blocnotas.database

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Notes (
    @PrimaryKey val id: Int,
    @NonNull @ColumnInfo(name = "note_title") val noteTitle: String,
    @NonNull @ColumnInfo(name = "note_text") val noteText: String
    )