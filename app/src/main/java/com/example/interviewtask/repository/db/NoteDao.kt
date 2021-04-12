package com.example.interviewtask.repository.db

import androidx.lifecycle.LiveData
import androidx.room.*
import java.time.chrono.ChronoLocalDateTime

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insert(user: Note)

    @Query("SELECT * FROM user ORDER BY id ASC")
    fun getAllUserData(): LiveData<List<Note>>

    @Query("UPDATE user SET isCheck =:isLive, DateTime=:dateTime WHERE id = :id")
    fun updateInto(id: Int, isLive:Boolean,dateTime:String)
}
