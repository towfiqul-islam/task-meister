package com.example.taskmeister.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface TaskDao {
    // Header related action
    @Insert
    fun addTaskHeader(header: TaskHeader)

    @Query("SELECT * FROM headers")
    fun getTaskHeaders(): LiveData<List<TaskHeader>>

    @Query("SELECT id FROM  headers WHERE taskHeader = :key")
    fun getHeaderId(key: String): LiveData<Int>

    // Deleting task header
    @Query("DELETE FROM headers WHERE id = :key")
    fun deleteHeader(key: Int)

    // Task Related action
    @Insert
    fun addTask(task: Task)

    @Query("SELECT * FROM tasks WHERE headerId = :key")
    fun getAllTasks(key: Int): LiveData<List<Task>>


    @Query("DELETE FROM tasks WHERE headerId = :key")
    fun deleteTasks(key: Int)

    // Update task
    @Update
    fun updateTask(task: Task)

}