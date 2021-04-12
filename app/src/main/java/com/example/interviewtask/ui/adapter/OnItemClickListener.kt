package com.example.interviewtask.ui.adapter

import com.example.interviewtask.repository.db.Note

interface OnItemClickListener {
    fun onItemClick(data: Note)
}