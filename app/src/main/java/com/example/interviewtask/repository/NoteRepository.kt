package com.example.interviewtask.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.example.interviewtask.repository.db.Note
import com.example.interviewtask.repository.db.NoteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class NoteRepository {

    companion object{
        var userDatabase: NoteDatabase?=null

        private fun intialiseDB(context:Context): NoteDatabase?
        {
            return NoteDatabase.getInstance(context)!!
        }

        fun insert(context: Context,user: Note)
        {
            userDatabase = intialiseDB(context)

            CoroutineScope(IO).launch {
                userDatabase!!.userDao().insert(user)
            }
        }
        fun update(context: Context, user: Note?)
        {
            userDatabase = intialiseDB(context)
            CoroutineScope(IO).launch {
                user?.id?.let { userDatabase!!.userDao().updateInto(user.id,user.isCheck,user.DateTime) }
            }
        }

        fun getAllUserData(context: Context): LiveData<List<Note>>
        {
            userDatabase = intialiseDB(context)
            return userDatabase!!.userDao().getAllUserData()
        }
    }
}
