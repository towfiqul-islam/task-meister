package com.example.taskmeister.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TaskDao {
    @Insert
    fun addTaskHeader(header: TaskHeader)

    @Query("SELECT * FROM headers")
    fun getTaskHeaders(): LiveData<List<TaskHeader>>
}