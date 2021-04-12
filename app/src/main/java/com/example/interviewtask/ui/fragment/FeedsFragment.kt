package com.example.interviewtask.ui.fragment

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.interviewtask.databinding.FeedAddDialogBinding
import com.example.interviewtask.databinding.FeedUpdateDialogBinding
import com.example.interviewtask.databinding.FeedsFragmentBinding
import com.example.interviewtask.repository.db.Note
import com.example.interviewtask.ui.adapter.FeedAdapter
import com.example.interviewtask.ui.adapter.OnItemClickListener
import com.example.interviewtask.ui.viewmodel.NotesViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class FeedFragment : Fragment(), OnItemClickListener {

    private var _binding: FeedsFragmentBinding? = null
    private val binding get() = _binding!!
    lateinit var dialog: BottomSheetDialog
    lateinit var dialogBinding: FeedAddDialogBinding
    lateinit var updatedialogBinding: FeedUpdateDialogBinding
    private lateinit var notesViewModel: NotesViewModel
    private lateinit var userAdapter: FeedAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FeedsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        userAdapter = FeedAdapter(requireContext(), ArrayList<Note>(), this)
        binding.apply {
            recyclerView.setHasFixedSize(true)
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            recyclerView.adapter = userAdapter
        }
        notesViewModel = ViewModelProvider(this).get(NotesViewModel::class.java)
        notesViewModel.getAllUserData(requireContext()).observe(requireActivity(), Observer {
            if (it.size > 0) {
                var sortedList = it.sortedByDescending(({ it.isCheck }))
                userAdapter = FeedAdapter(
                    requireContext(),
                    sortedList.toMutableList() as ArrayList<Note>,
                    this
                )
                binding.apply {
                    recyclerView.setHasFixedSize(true)
                    recyclerView.layoutManager = LinearLayoutManager(requireContext())
                    recyclerView.adapter = userAdapter
                }
                userAdapter.notifyDataSetChanged()
            }
        })

        binding.floatingActionButton.setOnClickListener {
            openDialog()
        }
    }

    private fun openDialog() {
        dialog = BottomSheetDialog(requireContext())
        dialogBinding = FeedAddDialogBinding.inflate(LayoutInflater.from(context))
        dialog.setContentView(dialogBinding.root)

        dialogBinding.saveBtn.setOnClickListener {
            saveDataIntoDatabase()
        }
        dialog.show()
        dialogBinding.cancelBtn.setOnClickListener {
            dialog.dismiss()
        }
    }

    private fun saveDataIntoDatabase() {
        val getName = dialogBinding.nameEdt.text.toString().trim()
        val isLive = dialogBinding.checkBoxFinished.isChecked

        if (!TextUtils.isEmpty(getName)) {
            notesViewModel.insert(
                requireContext(),
                Note(0, getName, isLive, getCurrentDateTime(Date()))
            )
            Toast.makeText(requireContext(), "Data added successfully..", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        } else {
            Toast.makeText(requireContext(), "Please fill all the fields..", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun updateDialog(note: Note) {
        dialog = BottomSheetDialog(requireContext())
        updatedialogBinding = FeedUpdateDialogBinding.inflate(LayoutInflater.from(context))
        dialog.setContentView(updatedialogBinding.root)
        updatedialogBinding.nameEdt.setText(note.name.toString())
        updatedialogBinding.checkBoxFinished.isChecked = note.isCheck
        updatedialogBinding.saveBtn.setOnClickListener {
            updateDataIntoDatabase(
                Note(
                    note.id,
                    note.name,
                    updatedialogBinding.checkBoxFinished.isChecked,
                    getCurrentDateTime(Date())
                )
            )
        }
        dialog.show()
        updatedialogBinding.cancelBtn.setOnClickListener {
            dialog.dismiss()
        }
    }

     fun getCurrentDateTime(date: Date): String {
        return SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH).format(date)
    }

    private fun updateDataIntoDatabase(name: Note) {

        notesViewModel.updateInto(requireContext(), name)
        Toast.makeText(requireContext(), "Data update successfully..", Toast.LENGTH_SHORT).show()
        dialog.dismiss()

    }

    override fun onPause() {
        super.onPause()
        binding == null
    }

    override fun onItemClick(data: Note) {
        updateDialog(data)
    }
}
