package com.example.taskmeister.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "headers")
data class TaskHeader(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val taskHeader: String
)