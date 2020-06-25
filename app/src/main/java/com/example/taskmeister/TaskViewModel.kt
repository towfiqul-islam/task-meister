package com.example.taskmeister

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.taskmeister.database.Task
import com.example.taskmeister.database.TaskDao
import com.example.taskmeister.database.TaskHeader
import kotlinx.coroutines.*

class TaskViewModel (
    val database: TaskDao,
    application: Application
) : AndroidViewModel(application) {

    // Declaring a Coroutine job
    private var viewModelJob = Job()

    // Declaring a Coroutine Scope
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)


    // getting all task headers from taskTitle
    val taskHeads = database.getTaskHeaders()


    // Adding header start -->
    private suspend fun addHeader(taskHeader: TaskHeader) {
        withContext(Dispatchers.IO) {
            database.addTaskHeader(taskHeader)
        }
    }

    fun onAddNewHeader(header: String) {
        uiScope.launch {
            val taskHeader = TaskHeader(taskHeader = header)
            addHeader(taskHeader)
        }
    }
    // Adding header end <---


    // Clear threads after coroutine does it's work
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}