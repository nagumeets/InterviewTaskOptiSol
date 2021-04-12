package com.example.interviewtask.repository.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Note::class],version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun userDao():NoteDao

    companion object{
        @Volatile
        var instance:NoteDatabase?=null
        private const val DATABASE_NAME="User"

        fun getInstance(context: Context):NoteDatabase?
        {
            if(instance == null)
            {
                synchronized(NoteDatabase::class.java)
                {
                    if(instance == null)
                    {
                        instance= Room.databaseBuilder(context,NoteDatabase::class.java,
                            DATABASE_NAME)
                            .fallbackToDestructiveMigration()
                            .build()
                    }
                }
            }

            return instance
        }

    }
}