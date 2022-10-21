package com.example.blocnotas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.blocnotas.databinding.FragmentNewNoteBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class NewNoteFragment : Fragment() {

    private var _binding: FragmentNewNoteBinding? = null
    private val viewModel: NoteViewModel by viewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentNewNoteBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.sendNoteButton.setOnClickListener {
            viewModel.createNote(noteTitle = binding.noteTitle.text.toString(), noteText = binding.noteText.text.toString())
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}