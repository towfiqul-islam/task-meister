package com.example.taskmeister

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.taskmeister.database.TaskDao
import java.lang.IllegalArgumentException

class TaskViewModelFactory (
    private val dataSource: TaskDao,
    private val application: Application
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TaskViewModel::class.java)) {
            return TaskViewModel(dataSource, application) as T
        }

        throw IllegalArgumentException("Unknown viewModel class")
    }
}