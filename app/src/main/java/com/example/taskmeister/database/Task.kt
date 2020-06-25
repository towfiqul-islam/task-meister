package com.example.taskmeister.database

import androidx.room.*

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true) val taskId: Int=0,
     val headerId: Int,
     val itemName: String,
     var isCompleted: Boolean = false
)