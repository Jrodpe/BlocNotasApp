package com.example.blocnotas.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewParent
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.blocnotas.database.Notes
import com.example.blocnotas.databinding.NotesViewBinding

class NotesListAdapter(/*private val onItemClicked: (Notes) -> Unit*/): ListAdapter<Notes, NotesListAdapter.NotesViewHolder>(DiffCallBack) {

    companion object {
        private val DiffCallBack = object : DiffUtil.ItemCallback<Notes>(){
            override fun areContentsTheSame(oldItem: Notes, newItem: Notes): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(oldItem: Notes, newItem: Notes): Boolean {
                return  oldItem.noteTitle == newItem.noteTitle
            }
        }
    }

    class NotesViewHolder(private var binding: NotesViewBinding): RecyclerView.ViewHolder(binding.root){
        @SuppressLint("SimpleDataFormat")
        fun bind(notes: Notes){
            binding.titulo.text = notes.noteTitle
            binding.cuerpo.text = notes.noteText
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder{
        val viewHolder = NotesViewHolder(
            NotesViewBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false)
        )
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            //onItemClicked(getItem(position))
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


}