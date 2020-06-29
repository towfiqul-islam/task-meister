package com.example.taskmeister.database

import androidx.room.*

@Entity(
    tableName = "tasks",
    foreignKeys = [
        ForeignKey(entity = TaskHeader::class,
            parentColumns = ["id"],
            childColumns = ["headerId"],
            onDelete = ForeignKey.CASCADE,
            onUpdate =  ForeignKey.CASCADE)
    ],
    indices = [Index("headerId")]
)
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int=0,
    val headerId: Int,
    val itemName: String,
    var isCompleted: Boolean = false,
    val taskHeader: String = ""
)