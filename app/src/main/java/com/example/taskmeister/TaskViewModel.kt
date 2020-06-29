package com.example.taskmeister

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.taskmeister.database.Task
import com.example.taskmeister.database.TaskDao
import com.example.taskmeister.database.TaskHeader
import kotlinx.coroutines.*

class TaskViewModel (
    val database: TaskDao,
    application: Application,
    val header_name: String,
    val header_id: Int

) : AndroidViewModel(application) {

    // Declaring a Coroutine job
    private var viewModelJob = Job()

    // Declaring a Coroutine Scope
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)


    // getting all task headers from taskTitle
    val taskHeads = database.getTaskHeaders()

    // Getting tasks
   val tasks = database.getAllTasks(header_id)

    fun getIndividualTasks(header_id: Int): LiveData<List<Task>> {
        return database.getAllTasks(header_id)
    }


    // Deleting tasks
    private suspend fun delete(key: Int) {
        withContext(Dispatchers.IO) {
            database.deleteTasks(key)
        }
    }

    fun onDelete(key: Int) {
        uiScope.launch {
            delete(key)
        }
    }

    // Deleting Task Header
    private suspend fun deleteHeader(key: Int) {
        withContext(Dispatchers.IO) {
            database.deleteHeader(key)
        }
    }

    fun onDeleteHeader(key: Int) {
        uiScope.launch {
            deleteHeader(key)
        }
    }

    // Updating task
    private suspend fun updateTask(task: Task) {
        withContext(Dispatchers.IO) {
            database.updateTask(task)
        }
    }

    fun onUpdateTask(task: Task) {
        uiScope.launch {
            updateTask(task)
        }
    }

    // getting header id
//    val headerId = database.getHeaderId(header_name)

    fun getHeaderId(header_name: String): LiveData<Int> {
        return database.getHeaderId(header_name)
    }


    // Adding task
    private suspend fun addTask(task: Task) {
        withContext(Dispatchers.IO) {
            database.addTask(task)
        }
    }

    fun onAddTask(headerId: Int, itemName: String) {
        uiScope.launch {
            val task = Task(headerId = headerId, itemName = itemName)
            addTask(task)
        }
    }

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