package com.example.blocnotas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.blocnotas.databinding.FragmentNewNoteBinding
import com.example.blocnotas.viewmodels.NotesViewModel
import com.example.blocnotas.viewmodels.NotesViewModelFactory

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class NewNoteFragment : Fragment() {

    private var _binding: FragmentNewNoteBinding? = null
    private val viewModel: NotesViewModel by activityViewModels {
        NotesViewModelFactory(
            (activity?.application as NotesApplication).database.noteDao()
        )
    }

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

    /**
     * Returns true if the EditTexts are not empty
     */
    private fun isEntryValid(): Boolean {
        return viewModel.isEntryValid(
            binding.noteTitle.text.toString(),
            binding.noteText.text.toString()
        )
    }

    private fun addNewNote() {
        if (isEntryValid()) {
            viewModel.addNewNote(
                binding.noteTitle.text.toString(),
                binding.noteText.text.toString()
            )
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val id = arguments?.getString(ARG_NOTE_ID)
        val title = arguments?.getString(ARG_NOTE_TITLE)
        val text = arguments?.getString(ARG_NOTE_TEXT)

        if (id != null && title != null && text != null) {
            binding.sendNoteButton.text = getString(R.string.edit_note_button)
            binding.noteTitle.setText(title)
            binding.noteText.setText(text)
            (requireActivity() as MainActivity).supportActionBar?.title =
                getString(R.string.edit_note_button)
            binding.sendNoteButton.setOnClickListener {
                viewModel.editNote(
                    id.toInt(),
                    binding.noteTitle.text.toString(),
                    binding.noteText.text.toString()
                )
                findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
            }
        } else {
            binding.sendNoteButton.setOnClickListener {
                addNewNote()
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val ARG_NOTE_ID = "noteId"
        const val ARG_NOTE_TITLE = "noteTitle"
        const val ARG_NOTE_TEXT = "noteText"
    }

}