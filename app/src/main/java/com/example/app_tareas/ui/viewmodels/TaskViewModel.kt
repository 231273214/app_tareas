package com.example.app_tareas.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.app_tareas.data.database.TaskDao
import com.example.app_tareas.data.entities.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.util.Date

class TaskViewModelFactory(private val taskDao: TaskDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TaskViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TaskViewModel(taskDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

class TaskViewModel(private val taskDao: TaskDao) : ViewModel() {
    val allTasks: Flow<List<Task>> = taskDao.getAllTasks()
    val pendingTasks: Flow<List<Task>> = taskDao.getPendingTasks()
    val allGroups: Flow<List<String>> = taskDao.getAllGroups()

    fun searchTasks(query: String): Flow<List<Task>> = taskDao.searchTasks("%$query%")

    fun addTask(
        title: String,
        description: String,
        priority: Task.Priority,
        group: String,
        dueDate: Date
    ) {
        viewModelScope.launch {
            val task = Task(
                title = title,
                description = description,
                priority = priority,
                group = group,
                creationDate = Date(),
                dueDate = dueDate,
                completionDate = null
            )
            taskDao.insertTask(task)
        }
    }

    fun updateTask(task: Task) {
        viewModelScope.launch {
            taskDao.updateTask(task)
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            taskDao.deleteTask(task)
        }
    }

    fun completeTask(task: Task) {
        viewModelScope.launch {
            val completedTask = task.copy(
                isCompleted = true,
                completionDate = Date()
            )
            taskDao.updateTask(completedTask)
        }
    }
}