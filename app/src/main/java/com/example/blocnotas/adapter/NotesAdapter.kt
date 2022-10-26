package com.example.blocnotas.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.blocnotas.R
import com.example.blocnotas.database.Notes

class NotesAdapter(): RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    private var noteList: List<Notes> = listOf()

    class NotesViewHolder(private val view: View): RecyclerView.ViewHolder(view){
        val tituloNotas: TextView = view.findViewById(R.id.titulo)
        val textoNotas: TextView = view.findViewById(R.id.cuerpo)

        fun render(nota: Notes){
            tituloNotas.text = nota.noteTitle
            textoNotas.text = nota.noteText
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        /**Creamos una nueva lista*/
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.notes_view, parent, false)

        return NotesViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val nota = noteList[position]
        holder.render(nota)
    }

    /**La función [getItemCount] debe mostrar el tamaño del conjunto de datos*/
    override fun getItemCount() = noteList.size

    fun submitList(notes: List<Notes>){
        noteList = notes
        notifyDataSetChanged() //avisamos al adapter de cambios para repintarlo
    }

}