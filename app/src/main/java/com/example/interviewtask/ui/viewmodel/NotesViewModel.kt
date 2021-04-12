package com.example.interviewtask.ui.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.interviewtask.repository.NoteRepository
import com.example.interviewtask.repository.db.Note

internal class NotesViewModel : ViewModel(){

    fun insert(context: Context, user: Note) {
        NoteRepository.insert(context, user)
    }
    fun updateInto(context: Context,user: Note?) {
        NoteRepository.update(context,user)
    }
    fun getAllUserData(context: Context): LiveData<List<Note>> {
        return NoteRepository.getAllUserData(context)
    }
}