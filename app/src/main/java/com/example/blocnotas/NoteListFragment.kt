package com.example.blocnotas

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.blocnotas.adapter.NotesListAdapter
import com.example.blocnotas.database.Notes
import com.example.blocnotas.databinding.FragmentNoteListBinding
import com.example.blocnotas.viewmodels.NotesViewModel
import com.example.blocnotas.viewmodels.NotesViewModelFactory
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class NoteListFragment : Fragment() {

    private val adapter = NotesListAdapter (deleteItemClickListener = { noteToDelete -> deleteNote(noteToDelete)},
                                            updateItemClickListener = { noteToUptdate -> updateNote(noteToUptdate)})

    private var _binding: FragmentNoteListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: NotesViewModel by activityViewModels {
        NotesViewModelFactory(
            (activity?.application as NotesApplication).database.noteDao()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentNoteListBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = binding.recyclerNotes
        recyclerView.adapter = adapter

        binding.addButton.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        lifecycle.coroutineScope.launch {
            viewModel.allNotes().collect() { newNotes ->
                adapter.submitList(newNotes)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun deleteNote(notes: Notes) {
        viewModel.deleteNote(notes)
    }

    private fun updateNote(notes: Notes){
        val bundle = bundleOf("noteId" to notes.id.toString(),
                                        "noteTitle" to notes.noteTitle,
                                        "noteText" to notes.noteText)
        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundle)
    }
}