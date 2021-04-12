package com.example.interviewtask.repository.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class Note(@PrimaryKey(autoGenerate = true)
                var id:Int=0,val name: String, val isCheck: Boolean, val DateTime: String){

}